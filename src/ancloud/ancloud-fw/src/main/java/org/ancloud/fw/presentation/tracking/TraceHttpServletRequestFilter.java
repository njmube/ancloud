package org.ancloud.fw.presentation.tracking;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;

public class TraceHttpServletRequestFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(TraceHttpServletRequestFilter.class);
	private static final long DEFAULT_WARN_NANOS = TimeUnit.SECONDS.toNanos(3L);
	private static final String MDC_TRACKING_ID = "MDC_TRACKING_ID";
	private long warnHandlingNanos;
	private static final Pattern UUID_REPLACE_PATTERN = Pattern.compile("-");
	
	public TraceHttpServletRequestFilter() {
		this.warnHandlingNanos = DEFAULT_WARN_NANOS;
	}

	protected static String buildMethodParams(HandlerMethod handlerMethod) {
		MethodParameter[] params = handlerMethod.getMethodParameters();
		List<String> lst = new ArrayList<String>(params.length);
		for (MethodParameter p : params) {
			lst.add(p.getParameterType().getSimpleName());
		}
		return StringUtils.collectionToCommaDelimitedString(lst);
	}

	public void setWarnHandlingNanos(long warnHandlingNanos) {
		this.warnHandlingNanos = warnHandlingNanos;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String contentType = request.getContentType();
		String accept = request.getHeader("Accept");
		HttpSession session = request.getSession(true);
		
		if(!(contentType == null || !(contentType.equals("application/json") 
				|| contentType.equals("application/xml")
				|| contentType.equals("text/html")
				|| contentType.equals("text/json")
				|| contentType.equals("text/xml")
				))
			||
			!(accept == null || !(accept.equals("text/html+xml") 
					|| accept.contains("text/html")
					))
			&  logger.isDebugEnabled()){
			
			long startTime = System.nanoTime();
			MDC.put(MDC_TRACKING_ID, createTrackingId()+"_"+session.getId());
			logger.debug("[START SERVLET REQUEST]");
			if(request instanceof HttpServletRequest){
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
						ipAddress = request.getRemoteAddr();
				}
				logger.debug("{} {}?{}", new Object[] {
						ipAddress,
						request.getRequestURL(),
						request.getQueryString(),
				});
				logger.debug("{}", new Object[] {
						request.getHeader("User-Agent"),
				});
				
				if(!(request.getContentType() == null || !(request.getContentType().equals("application/json") ||
						request.getContentType().equals("application/xml")))){
						request = new MultiReadHttpServletRequest(request);
						logger.trace(IOUtils.toString(request.getInputStream()));
				}
			}
			try {
				filterChain.doFilter(request, response);
			} finally {
				long handlingTime = System.nanoTime() - startTime;
				String formattedHandlingTime = String.format("%1$,3d",new Object[] { TimeUnit.MILLISECONDS.toSeconds(handlingTime) });
				boolean isWarnHandling = handlingTime > this.warnHandlingNanos;
				logger.debug("[END SERVLET REQUEST]", new Object[] {});
	
				String handlingTimeMessage = "[HANDLING TIME] {} ms";
				if (isWarnHandling & logger.isWarnEnabled()) {
					logger.warn(handlingTimeMessage + " > {}", 
							new Object[] {formattedHandlingTime,
									TimeUnit.MILLISECONDS.toSeconds(this.warnHandlingNanos)
							});
				} else {
					logger.debug(handlingTimeMessage, new Object[] { formattedHandlingTime });
				}
				MDC.remove(MDC_TRACKING_ID);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	protected String createTrackingId() {
		String uuid = UUID.randomUUID().toString();
		String xTrack = UUID_REPLACE_PATTERN.matcher(uuid).replaceAll("");
		return xTrack;
	}

	public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

		private byte[] body;

		public MultiReadHttpServletRequest(HttpServletRequest httpServletRequest) {
			super(httpServletRequest);
			try {
				body = IOUtils.toByteArray(super.getInputStream());
			} catch (IOException e) {
				logger.error("Cannot read from HttpServletRequest input stream.",e);
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new ServletInputStreamImpl(new ByteArrayInputStream(body));
		}

		@Override
		public BufferedReader getReader() throws IOException {
			String enc = getCharacterEncoding();
			if (enc == null)
				enc = "UTF-8";
			return new BufferedReader(new InputStreamReader(getInputStream(), enc));
		}

		private class ServletInputStreamImpl extends ServletInputStream {

			private InputStream is;

			public ServletInputStreamImpl(InputStream is) {
				this.is = is;
			}

			public int read() throws IOException {
				return is.read();
			}

			public boolean markSupported() {
				return is.markSupported();
			}

			public synchronized void mark(int i) {
				is.mark(i);
			}

			public synchronized void reset() throws IOException {
				is.reset();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
			}
		}
	}
}