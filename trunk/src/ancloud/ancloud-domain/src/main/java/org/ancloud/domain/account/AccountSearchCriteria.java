package org.ancloud.domain.account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.repository.utils.CriteriaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class AccountSearchCriteria extends Account implements Specification<Account> {

	private static final long serialVersionUID = -6019784132716027681L;

	@Override
	public Predicate toPredicate(Root<Account> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriabuilder) {
		Predicate predicate =  null;
		if(StringUtils.isNotBlank(this.getName())){
			predicate = criteriabuilder.or(
					criteriabuilder.like(
							criteriabuilder.lower(root.<String>get("name"))
							,CriteriaUtil.getContainsLikePattern(this.getName())
						)
					,criteriabuilder.like(
							criteriabuilder.lower(root.<String>get("userName"))
							,CriteriaUtil.getContainsLikePattern(this.getName())
						)
				);
		} else {
			predicate = criteriabuilder.or(
							criteriabuilder.isNull(root.<String>get("name"))
							,criteriabuilder.isNotNull(root.<String>get("name"))
							);
		}
		if(this.getAccountStatus() != null){
			predicate = criteriabuilder.and(predicate
									,criteriabuilder.equal(root.<Boolean>get("accountStatus")
														,this.getAccountStatus()
													)
							);
		}
		if(this.getAccountType() != null){
			predicate = criteriabuilder.and(predicate
									,criteriabuilder.equal(root.<AccountType>get("accountType")
														,this.getAccountType()
													)
							);
		}
		return predicate;
	}
}
