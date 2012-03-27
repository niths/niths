package no.niths.domain.adapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import no.niths.common.AppConstants;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonCalendarDeserializerAdapter extends JsonDeserializer<Calendar> {

	private Logger logger = LoggerFactory
			.getLogger(JsonCalendarDeserializerAdapter.class);
	private DateFormat df = new SimpleDateFormat(AppConstants.CALENDAR_FORMAT);

	@Override
	public Calendar deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException { 
		Calendar calendar = new GregorianCalendar();		
		try {
			calendar.setTime(df.parse(jp.getText()));
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return calendar;
	}
}
