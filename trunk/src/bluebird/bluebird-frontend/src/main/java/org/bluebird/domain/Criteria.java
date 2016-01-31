package org.bluebird.domain;

public abstract class Criteria extends BaseModel {

	private static final long serialVersionUID = -5856871301153802424L;

	public String getContainsLikePattern(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		} else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}
}
