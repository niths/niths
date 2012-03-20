package no.niths.domain.respons;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import no.niths.domain.Event;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class EventResponsTest {

	private Event event;
	private String name = "St. patrick's day";
	private String description = "St. Patrick’s Day is observed on 17";
	private GregorianCalendar startTime = new GregorianCalendar(2012,
			GregorianCalendar.MARCH, 16, 17, 00);
	private GregorianCalendar endTime = new GregorianCalendar(2012,
			GregorianCalendar.MARCH, 17, 5, 00);
	private String tags = name + "&Ireland&Beer";
	private Double longitude = 59.909052;
	private Double latitude = 10.766940;

	private final String encoding = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	private String XMLShouldProduce = encoding + "<event>" + "<name>" + name
			+ "</name>" + "<description>" + description + "</description>"
			+ "<startTime>16/03/2012-17:00</startTime>"
			+ "<endTime>17/03/2012-05:00</endTime>" + "<latitude>" + latitude
			+ "</latitude>" + "<longitude>" + longitude + "</longitude>"
			+ "<tags>St. patrick's day&amp;Ireland&amp;Beer</tags>"
			+ "</event>";

	private String JSONShouldProduce = "{\"name\":\"St. patrick's day\","
			+ "\"description\":\"St. Patrick’s Day is observed on 17\","
			+ "\"startTime\":\"16/03/2012-17:00\","
			+ "\"endTime\":\"17/03/2012-05:00\"," + "\"latitude" + "\":"
			+ latitude + ",\"longitude" + "\":" + longitude + ",\"tags\":"
			+ "\"" + tags + "\"}";

	@Before
	public void setUp() {
		event = new Event(name, description, startTime, endTime);
//		event.setLatitude(latitude);
//		event.setLongitude(longitude);
		event.setTags(tags);
	}

	@Test
	public void testXMLrespons() {
		try {
			JAXBContext context = JAXBContext.newInstance(Event.class);

			Marshaller m = context.createMarshaller();
			// m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter out = new StringWriter();
			m.marshal(event, new StreamResult(out));

			assertEquals(XMLShouldProduce, out.toString());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJSONrespons() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			assertEquals(JSONShouldProduce, mapper.writeValueAsString(event));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
