package no.niths.application.rest.helper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import no.niths.application.rest.exception.CustomParseException;
import no.niths.common.AppConstants;

public class TimeDTO implements Serializable {
	private static final long serialVersionUID = 9063546904075958216L;

	private DateFormat df = new SimpleDateFormat(AppConstants.CALENDAR_FORMAT);
	
	private String startTime;
	
	private String endTime;
	
	private GregorianCalendar calendar = new GregorianCalendar();

	public TimeDTO() {
		this(null,null);
	}
	
	public TimeDTO(String start, String end){
		setStartTime(start);
		setEndTime(end);
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public GregorianCalendar getEndTimeCal() {
		return parseHelper(endTime);
	}

	private GregorianCalendar parseHelper(String parseableString) {
		try {
			calendar.setTime(df.parse(parseableString));
			return calendar;
		} catch (ParseException px) {
			throw new CustomParseException("Invalid syntacs! Valid syntax : "
					+ AppConstants.CALENDAR_FORMAT + " ErrorOffset:"
					+ px.getErrorOffset());
		}
	}

	public GregorianCalendar getStartTimeCal() {
		return parseHelper(startTime);
	}

	@Override
	public String toString() {
		return String.format("[%s][%s]", startTime, endTime);
	}
}
