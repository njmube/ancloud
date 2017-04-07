package org.ancloud.domain.account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.Specification;

public class AccountPermissionSearchCriteria extends AccountPermission implements Specification<AccountPermission> {

	@Override
	public Predicate toPredicate(Root<AccountPermission> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = criteriaBuilder.isNull(root.<DateTime>get("deletedDate"));
		return predicate;
	}
}
