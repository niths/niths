package no.niths.common;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class CalendarAdapter extends XmlAdapter<String, Calendar>{

	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");  
	
	@Override
	public Calendar unmarshal(String date) throws ParseException {
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(df.parse(date));
		return calendar;
	}

	@Override
	public String marshal(Calendar calendar) throws ParseException {
		
		return df.format(calendar.getTime());
	}
	

}
