package org.ancloud.service.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.Permission;
import org.ancloud.domain.account.Role;
import org.ancloud.domain.utils.SystemCodeConstant;
import org.ancloud.repository.account.AccountRepository;
import org.ancloud.service.account.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Inject
	AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl userDetails = null;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Account userAccount = accountRepository.findByUserName(username);
		if (userAccount == null) {
			throw new UsernameNotFoundException(SystemCodeConstant.ERR_SYS_LOGIN_USERNOTFOUND); 
		}
		
		Set<Permission> permissions = userAccount.getPermissions();
		for (Role role : userAccount.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
			permissions.addAll(role.getPermissions());
		}
		
		for (Permission permission : permissions) {
			GrantedAuthority authority = new SimpleGrantedAuthority(permission.getCode());
			authorities.add(authority);
		}
		userDetails = new UserDetailsImpl(userAccount,authorities);
		
		return userDetails;
	}
}