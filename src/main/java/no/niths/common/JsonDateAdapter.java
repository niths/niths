package no.niths.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateAdapter extends JsonSerializer<Date>{
	private DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT);
	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider)
			throws IOException, JsonProcessingException {
		
		gen.writeString(df.format(date));
	}

}
