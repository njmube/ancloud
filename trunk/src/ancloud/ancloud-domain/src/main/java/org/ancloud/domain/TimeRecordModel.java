package org.ancloud.domain;

import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TimeRecordModel extends BaseModel{
	
	private DateTime recordedDate;

	public DateTime getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(DateTime recordedDate) {
		this.recordedDate = recordedDate;
	}
}
