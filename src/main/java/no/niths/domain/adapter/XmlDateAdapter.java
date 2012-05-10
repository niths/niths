package no.niths.domain.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import no.niths.application.rest.exception.CustomParseException;
import no.niths.common.constants.MiscConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class XmlDateAdapter used too parse a String too a date
 * or a date too a String
 */
public class XmlDateAdapter extends XmlAdapter<String,Date>{

    private Logger logger = LoggerFactory
            .getLogger(XmlDateAdapter.class);
    private DateFormat df = new SimpleDateFormat(MiscConstants.DATE_FORMAT);
    @Override
    public Date unmarshal(String date){
        
        try {
            return df.parse(date);
        } catch (ParseException px) {
            logger.error(px.getMessage(),px);
            throw new CustomParseException("Invalid syntacs! Valid syntax : "
                    + MiscConstants.DATE_FORMAT + " ErrorOffset:"
                    + px.getErrorOffset());
        }
    }

    @Override
    public String marshal(Date date){
        return df.format(date);
    }

}
