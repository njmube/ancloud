package org.bluebird.domain.module.account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bluebird.domain.Criteria;
import org.springframework.data.jpa.domain.Specification;

public class AccountSearchCriteria extends Criteria implements Specification<Account> {

	private static final long serialVersionUID = -6019784132716027681L;

	private String userName;

	private String password;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Predicate toPredicate(Root<Account> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriabuilder) {
		String containsLikePattern = getContainsLikePattern(userName);
		return criteriabuilder.and(criteriabuilder.like(criteriabuilder.lower(root.<String>get("userName")),containsLikePattern));
	}
	
}
