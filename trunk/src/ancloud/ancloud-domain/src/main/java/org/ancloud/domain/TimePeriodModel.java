package org.ancloud.domain;

import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TimePeriodModel extends BaseModel{
	
	private DateTime fromDate;
	
	private DateTime toDate;

	public DateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
	}

	public DateTime getToDate() {
		return toDate;
	}

	public void setToDate(DateTime toDate) {
		this.toDate = toDate;
	}
}
