package org.ancloud.fw.core.joda;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeEditor extends PropertyEditorSupport {

	private DateTimeFormatter formatter;
	
	public DateTimeEditor(String pattern){
		this.formatter = DateTimeFormat.forPattern(pattern);
	}
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		DateTime dateTime = null;
		try{
			dateTime = this.formatter.parseDateTime(text);
		} catch(IllegalArgumentException ex){
			dateTime = null;
		}
		setValue(dateTime);
	}

	@Override
	public String getAsText() throws IllegalArgumentException {
		if(getValue()!=null){
			return ((DateTime)getValue()).toString(formatter);
		} else return StringUtils.EMPTY;
	}
}