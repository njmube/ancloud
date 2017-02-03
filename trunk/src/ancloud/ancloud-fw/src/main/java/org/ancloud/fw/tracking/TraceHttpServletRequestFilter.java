package org.ancloud.fw.tracking;

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
		long startTime = System.nanoTime();
		MDC.put(MDC_TRACKING_ID, createTrackingId());
		if(logger.isTraceEnabled()) {
			logger.trace("[START SERVLET REQUEST]");
			if(request instanceof HttpServletRequest){
				logger.trace("HTTP {} {} {}", new Object[] {
						request.getMethod(),
						request.getRequestURI(),
						request.getHeader("content-type")
				});
				
				if(!(request.getContentType() == null || !(request.getContentType().equals("application/json") ||
						request.getContentType().equals("application/xml")))){
						request = new MultiReadHttpServletRequest(request);
						logger.trace(IOUtils.toString(request.getInputStream()));
				}
			}
		}
		try {
			filterChain.doFilter(request, response);
		} finally {
			if(logger.isTraceEnabled()) {
				long handlingTime = System.nanoTime() - startTime;
				String formattedHandlingTime = String.format("%1$,3d",new Object[] { Long.valueOf(handlingTime) });
				boolean isWarnHandling = handlingTime > this.warnHandlingNanos;
				if (isWarnHandling) {
					if (!(logger.isWarnEnabled()))
						return;
				} else if (!(logger.isTraceEnabled())) {
					return;
				}
				logger.trace("[END SERVLET REQUEST] {}.{}({})-> view={}, model={}",
						new Object[] {});
	
				String handlingTimeMessage = "[HANDLING TIME] {} ns";
				if (isWarnHandling) {
					logger.warn(handlingTimeMessage + " > {}", 
							new Object[] {formattedHandlingTime,
										Long.valueOf(this.warnHandlingNanos) 
							});
				} else {
					logger.trace(handlingTimeMessage, new Object[] { formattedHandlingTime });
				}
			}
			MDC.remove(MDC_TRACKING_ID);
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
				return false;
			}

			public synchronized void mark(int i) {
				throw new RuntimeException(new IOException("mark/reset not supported"));
			}

			public synchronized void reset() throws IOException {
				throw new IOException("mark/reset not supported");
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
				
			}
		}


	}
}