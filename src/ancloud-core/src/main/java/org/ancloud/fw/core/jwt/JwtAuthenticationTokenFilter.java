package org.ancloud.fw.core.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter  implements Ordered{

	protected JwtAuthenticationTokenFilter() {
		super("/auth");
	}

	@Value("${ancloud.security.token.header}")
	private String tokenHeaderName;

	@Inject
	private JwtTokenHelper jwtTokenHelper;

	@Inject
	private UserDetailsService userDetailsService;

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String authToken = request.getHeader(this.tokenHeaderName);
		
		if (!(authToken == null || !authToken.startsWith("Bearer "))) {
			authToken = authToken.substring(7);
			String username = jwtTokenHelper.getUsernameFromToken(authToken);
	
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	
				// It is not compelling necessary to load the use details from the
				// database. You could also store the information
				// in the token and read it from it. It's up to you ;)
//				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				// For simple validation it is completely sufficient to just check
				// the token integrity. You don't have to call
				// the database compellingly. Again it's up to you ;)
//				if (tokenUtils.validateToken(authToken, userDetails)) {
				if (jwtTokenHelper.validateToken(authToken)) {
					List<GrantedAuthority> authorities = null;
					//jwtTokenHelper.getAuthorities(authToken);
					
					JwtAuthenticationToken authentication = new JwtAuthenticationToken(
							username, null, authorities);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					logger.info("authenticated user " + username + ", setting security context");
					return authentication;
				}
			}
		}
		return null;
	}

}
