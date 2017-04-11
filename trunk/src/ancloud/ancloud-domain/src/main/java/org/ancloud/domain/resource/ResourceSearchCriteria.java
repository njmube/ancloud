package org.ancloud.domain.resource;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ancloud.repository.utils.CriteriaUtil;
import org.springframework.data.jpa.domain.Specification;

public class ResourceSearchCriteria extends Resource implements Specification<Resource> {

	private static final long serialVersionUID = 6025788894371528636L;

	@Override
	public Predicate toPredicate(Root<Resource> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriabuilder) {
		return criteriabuilder.and(criteriabuilder.like(criteriabuilder.lower(root.<String>get("value")),CriteriaUtil.getContainsLikePattern(this.getValue())),
									criteriabuilder.equal(root.<String>get("category"), this.getCategory()));
	}
	
}
