package no.niths.domain.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import no.niths.common.AppConstants;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonCalendarAdapter extends JsonSerializer<Calendar> {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonCalendarAdapter.class);

	private DateFormat df = new SimpleDateFormat(AppConstants.CALENDAR_FORMAT);

	@Override
	public void serialize(Calendar calendar, JsonGenerator gen,
			SerializerProvider provider) {
		try {

			gen.writeString(df.format(calendar.getTime()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
