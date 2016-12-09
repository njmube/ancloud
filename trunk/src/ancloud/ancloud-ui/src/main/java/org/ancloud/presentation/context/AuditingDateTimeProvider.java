package org.ancloud.presentation.context;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AuditingDateTimeProvider implements DateTimeProvider {


	@Override
	public Calendar getNow() {
		return GregorianCalendar.getInstance();
	}
}