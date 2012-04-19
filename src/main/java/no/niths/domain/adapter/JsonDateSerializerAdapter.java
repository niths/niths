package no.niths.domain.adapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import no.niths.common.MiscConstants;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonDateSerializerAdapter extends JsonSerializer<Date> {
	private DateFormat df = new SimpleDateFormat(MiscConstants.DATE_FORMAT);
	private static final Logger logger = LoggerFactory
			.getLogger(JsonDateSerializerAdapter.class);

	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		try {
			if (date != null) {
				gen.writeString(df.format(date));
			} else {
				gen.writeNull();
			}

		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
