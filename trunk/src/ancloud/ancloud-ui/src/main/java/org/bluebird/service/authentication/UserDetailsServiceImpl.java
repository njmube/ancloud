package org.bluebird.service.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.bluebird.domain.common.SystemCodeConstant;
import org.bluebird.domain.modules.account.Account;
import org.bluebird.domain.modules.account.Permission;
import org.bluebird.domain.modules.account.Role;
import org.bluebird.repository.modules.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
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
			permissions.addAll(role.getPermissions());
		}
		
		for (Permission permission : permissions) {
			GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermissionCode());
			authorities.add(authority);
		}
		userDetails = new UserDetailsImpl(userAccount,authorities);
		
		return userDetails;
	}

}