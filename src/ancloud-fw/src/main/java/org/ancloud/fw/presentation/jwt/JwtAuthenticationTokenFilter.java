package org.ancloud.fw.presentation.jwt;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter implements Ordered{

	@Value("${ancloud.security.token.header}")
	private String tokenHeaderName;

	@Inject
	private TokenUtils tokenUtils;

	@Inject
	private UserDetailsService userDetailsService;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authToken = request.getHeader(this.tokenHeaderName);
		
		if (authToken == null || !authToken.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		authToken = authToken.substring(7);
		String username = tokenUtils.getUsernameFromToken(authToken);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// It is not compelling necessary to load the use details from the
			// database. You could also store the information
			// in the token and read it from it. It's up to you ;)
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			// For simple validation it is completely sufficient to just check
			// the token integrity. You don't have to call
			// the database compellingly. Again it's up to you ;)
			if (tokenUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
