package org.ancloud.domain.message;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ancloud.domain.utils.CriteriaUtil;
import org.springframework.data.jpa.domain.Specification;

public class MesageSearchCriteria extends Message implements Specification<Message> {

	private static final long serialVersionUID = -6019784132716027681L;

	@Override
	public Predicate toPredicate(Root<Message> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriabuilder) {
		return criteriabuilder.and(
				criteriabuilder.like(criteriabuilder.lower(root.<String>get("message")),CriteriaUtil.getContainsLikePattern(this.getMessage())),
				criteriabuilder.like(criteriabuilder.lower(root.<String>get("messageKey")),CriteriaUtil.getContainsLikePattern(this.getKey())));
	}
	
}
