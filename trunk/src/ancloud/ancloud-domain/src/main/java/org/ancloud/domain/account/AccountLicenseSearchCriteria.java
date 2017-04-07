package org.ancloud.domain.account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.ancloud.domain.utils.CriteriaUtil;
import org.springframework.data.jpa.domain.Specification;

public class AccountLicenseSearchCriteria extends AccountLicense implements Specification<AccountLicense> {

	private static final long serialVersionUID = -6019784132716027681L;

	@Override
	public Predicate toPredicate(Root<AccountLicense> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = criteriaBuilder.isNull(root.<DateTime>get("deletedDate"));
		if(!(this.getAccount()==null || this.getAccount().getId()==null)){
			predicate = criteriaBuilder.and(predicate
					,criteriaBuilder.equal(root.<Account>get("account").<Long>get("id"), this.getAccount().getId())
			);
		}
		if(StringUtils.isNotBlank(this.getCode())){
			predicate = criteriaBuilder.and(predicate
					,criteriaBuilder.like(root.<String>get("code"), CriteriaUtil.getContainsLikePattern(this.getCode()))
			);
		}
		
		if(!(this.getFromDate()==null & this.getToDate() == null)){
			if(this.getFromDate()==null){
				predicate = criteriaBuilder.and(predicate
						,criteriaBuilder.lessThanOrEqualTo(root.<DateTime>get("fromDate"), this.getToDate())
				);
			} else if(this.getToDate() == null){
				predicate = criteriaBuilder.and(predicate
						,criteriaBuilder.greaterThanOrEqualTo(root.<DateTime>get("toDate"), this.getFromDate())
				);
			} else {
				predicate = criteriaBuilder.and(predicate
						,criteriaBuilder.not(
							criteriaBuilder.or(
								criteriaBuilder.greaterThan(root.<DateTime>get("fromDate"), this.getToDate())
								,criteriaBuilder.lessThan(root.<DateTime>get("toDate"), this.getFromDate())
							)
						)
				);
			}
			
			
		}
		return predicate;
	}
}
