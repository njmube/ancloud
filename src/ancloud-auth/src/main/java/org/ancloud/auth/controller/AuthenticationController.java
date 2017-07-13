package org.ancloud.auth.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.ancloud.auth.vm.AuthenticationRequest;
import org.ancloud.auth.vm.AuthenticationResponse;
import org.ancloud.fw.core.jwt.JwtTokenHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.uadetector.internal.data.domain.Device;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

	@Inject
	JwtTokenHelper jwtTokenHelper;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest,Device device) throws AuthenticationException {
		// // Perform the authentication
		// Authentication authentication =
		// this.authenticationManager.authenticate(new
		// UsernamePasswordAuthenticationToken(
		// authenticationRequest.getUsername(),
		// authenticationRequest.getPassword()));
		// SecurityContextHolder.getContext().setAuthentication(authentication);
		//
		// // Reload password post-authentication so we can generate token
		// UserDetails userDetails =
		// this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		// String token = this.tokenUtils.generateToken(userDetails, device);
		//
		// // Return the token
		 return ResponseEntity.ok(new AuthenticationResponse());
	}

	@RequestMapping(value = "${cerberus.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
//		String token = request.getHeader(this.tokenHeader);
//		String username = TokenUtils.getUsernameFromToken(token);
//		CerberusUser user = (CerberusUser) this.userDetailsService.loadUserByUsername(username);
//		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
//			String refreshedToken = this.tokenUtils.refreshToken(token);
//			return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
//		} else {
//			return ResponseEntity.badRequest().body(null);
//		}
		return ResponseEntity.ok(new AuthenticationResponse());
	}
}
