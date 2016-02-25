package org.bluebird.domain.module.message;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bluebird.domain.common.CriteriaUtil;
import org.springframework.data.jpa.domain.Specification;

public class MessageSearchCriteria extends Message implements Specification<Message> {

	private static final long serialVersionUID = -6019784132716027681L;

	@Override
	public Predicate toPredicate(Root<Message> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriabuilder) {
		String containsLikePattern = CriteriaUtil.getContainsLikePattern(this.getMessage());
		return criteriabuilder.and(criteriabuilder.like(criteriabuilder.lower(root.<String>get("message")),containsLikePattern));
	}
	
}
