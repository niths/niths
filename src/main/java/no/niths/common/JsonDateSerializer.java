package no.niths.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Serialize Java.util.Date, which is not a common JSON. type, so we have to
 * create a custom serialize method.
 * 
 */
public class JsonDateSerializer extends JsonSerializer<Calendar> {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonDateSerializer.class);

	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

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
