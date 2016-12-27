package org.ancloud.fw.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.ancloud.domain.common.SystemCodeConstant;
import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.AuthenticationAccountActivity;
import org.ancloud.domain.modules.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.modules.account.Permission;
import org.ancloud.domain.modules.account.Role;
import org.ancloud.repository.modules.account.AccountRepository;
import org.ancloud.repository.modules.account.AuthenticationAccountActivityRepository;
import org.ancloud.fw.core.serviceimpl.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Inject
	AccountRepository accountRepository;
	
	@Inject
	AuthenticationAccountActivityRepository authenticationAccountActivityRepository;

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
			permissions.addAll(role.getPermissions());
		}
		
		for (Permission permission : permissions) {
			GrantedAuthority authority = new SimpleGrantedAuthority(permission.getCode());
			authorities.add(authority);
		}
		userDetails = new UserDetailsImpl(userAccount,authorities);
		authenticationAccountActivityRepository.save(new AuthenticationAccountActivity(userAccount,AuthenticationType.LogInSuccess));
		return userDetails;
	}

}