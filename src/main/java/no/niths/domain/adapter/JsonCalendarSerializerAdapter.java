package no.niths.domain.adapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import no.niths.common.constants.MiscConstantNames;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonCalendarSerializerAdapter extends JsonSerializer<Calendar> {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonCalendarSerializerAdapter.class);

	private DateFormat df = new SimpleDateFormat(MiscConstantNames.CALENDAR_FORMAT);

	@Override
	public void serialize(Calendar calendar, JsonGenerator gen,
			SerializerProvider provider) {
		try {
			if(calendar!= null){
				gen.writeString(df.format(calendar.getTime()));
			}else{
				gen.writeNull();
			}
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
