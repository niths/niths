package no.niths.domain.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import no.niths.common.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {

	private DateFormat df = new SimpleDateFormat(AppConstants.CALENDAR_FORMAT);
	private static final Logger logger = LoggerFactory
			.getLogger(XmlCalendarAdapter.class);

	@Override
	public Calendar unmarshal(String date) throws ParseException {
		try {

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(df.parse(date));
			return calendar;
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String marshal(Calendar calendar) throws ParseException {
		try {

			return df.format(calendar.getTime());
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return null;
	}

}
