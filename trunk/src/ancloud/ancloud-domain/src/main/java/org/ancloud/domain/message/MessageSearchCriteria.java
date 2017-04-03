package org.ancloud.domain.message;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ancloud.domain.utils.CriteriaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

public class MessageSearchCriteria extends Message implements Specification<Message> {

	private static final long serialVersionUID = -6019784132716027681L;

	@Override
	public Predicate toPredicate(Root<Message> root,CriteriaQuery<?> criteriaquery, CriteriaBuilder criteriabuilder) {
		if(StringUtils.isEmpty(this.getLanguage())){
			this.setLanguage("en");
			this.setCountry("US");
		} else {
			String[] langs=this.getLanguage().split("_");
			this.setLanguage(langs[0]);
			if(langs.length>1){
				this.setCountry(langs[1]);
			}
		}
		return criteriabuilder.and(
				criteriabuilder.like(criteriabuilder.lower(root.<String>get("message")),CriteriaUtil.getContainsLikePattern(this.getMessage()))
								,criteriabuilder.like(criteriabuilder.lower(root.<String>get("messageKey")),CriteriaUtil.getContainsLikePattern(this.getKey()))
								,criteriabuilder.like(criteriabuilder.lower(root.<String>get("basename")),CriteriaUtil.getContainsLikePattern(this.getBasename()))
								,(this.getProject()==null?criteriabuilder.isNull(root.<String>get("project")):criteriabuilder.equal(root.<String>get("project"), this.getProject()))
								,criteriabuilder.equal(root.<String>get("country"), this.getCountry())
								,criteriabuilder.equal(root.<String>get("language"), this.getLanguage())
								);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getMessage());
		builder.append(" ");
		builder.append(this.getBasename());
		builder.append(" ");
		builder.append(this.getLanguage().replace("_", " "));
		builder.append(" ");
		builder.append(this.getKey());
		return builder.toString();
	}
}
