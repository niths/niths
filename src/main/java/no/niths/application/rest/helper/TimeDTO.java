package no.niths.application.rest.helper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import no.niths.application.rest.exception.CustomParseException;
import no.niths.common.MiscConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeDTO implements Serializable {
	private static final long serialVersionUID = 9063546904075958216L;
	private String startTime;
	private String endTime;
private Logger logger = LoggerFactory.getLogger(TimeDTO.class);
	
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

	public GregorianCalendar getStartTimeCal() {
		return parseHelper(startTime);
	}

	@Override
	public String toString() {
		return String.format("[%s][%s]", startTime, endTime);
	}
	
	private GregorianCalendar parseHelper(String parseableString) {
		DateFormat df = new SimpleDateFormat(MiscConstants.CALENDAR_FORMAT);
		GregorianCalendar calendar = new GregorianCalendar(2012, Calendar.MARCH, 9, 22, 21, 23);
		try {	
			calendar.setTime(df.parse(parseableString));
			logger.debug(calendar.getTime()+"");
			return calendar;
		} catch (ParseException px) {
			logger.debug(px.getMessage(),px);
			throw new CustomParseException("Invalid syntacs! Valid syntax : "
					+ MiscConstants.CALENDAR_FORMAT + " ErrorOffset:"
					+ px.getErrorOffset());
		}
	}
}
