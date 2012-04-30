package no.niths.domain.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * Class XmlCharAdapter used too get the first character from a String
 * or return the entire String
 */
public class XmlCharAdapter extends XmlAdapter<String, Character> {

	@Override
	public Character unmarshal(String v) throws Exception {
		if(v != null){
			return v.charAt(0);
		}else{
			return null;
		}
	}

	@Override
	public String marshal(Character v) throws Exception {
		return v+ "";
	}

}
