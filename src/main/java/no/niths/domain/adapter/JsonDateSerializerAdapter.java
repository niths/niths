package no.niths.domain.adapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import no.niths.common.AppConstants;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDateSerializerAdapter extends JsonSerializer<Date>{
	private DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT);
	private static final Logger logger = LoggerFactory
			.getLogger(JsonDateSerializerAdapter.class);
	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider)
			throws IOException, JsonProcessingException {
		try{
			
			gen.writeString(df.format(date));
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}

}
