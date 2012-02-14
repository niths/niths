package no.niths.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlDateAdapter extends XmlAdapter<String,Date>{

	
	private DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT);  
	@Override
	public Date unmarshal(String date) throws Exception {
		return df.parse(date);
	}

	@Override
	public String marshal(Date date) throws Exception {
		return df.format(date);
	}

}
