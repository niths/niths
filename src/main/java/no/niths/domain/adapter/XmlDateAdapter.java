package no.niths.domain.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import no.niths.application.rest.exception.CustomParseException;
import no.niths.common.constants.MiscConstantNames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlDateAdapter extends XmlAdapter<String,Date>{

	private Logger logger = LoggerFactory
			.getLogger(XmlDateAdapter.class);
	private DateFormat df = new SimpleDateFormat(MiscConstantNames.DATE_FORMAT);
	@Override
	public Date unmarshal(String date){
		
		try {
			return df.parse(date);
		} catch (ParseException px) {
			logger.error(px.getMessage(),px);
			throw new CustomParseException("Invalid syntacs! Valid syntax : "
					+ MiscConstantNames.DATE_FORMAT + " ErrorOffset:"
					+ px.getErrorOffset());
		}
	}

	@Override
	public String marshal(Date date){
		return df.format(date);
	}

}
