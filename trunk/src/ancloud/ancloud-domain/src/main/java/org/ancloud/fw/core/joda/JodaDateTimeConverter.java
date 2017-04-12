package org.ancloud.fw.core.joda;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

//@Converter(autoApply = true)
public class JodaDateTimeConverter implements AttributeConverter<DateTime, Timestamp> {

	public Timestamp convertToDatabaseColumn(DateTime dateTime) {
		if(dateTime==null)
			return null;
		return new Timestamp(dateTime.withZone(DateTimeZone.UTC).getMillis());
	}

	public DateTime convertToEntityAttribute(Timestamp date) {
		return new DateTime(date);
	}
}