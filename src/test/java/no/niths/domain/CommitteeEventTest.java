package no.niths.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommitteeEventTest {

	private static final Logger logger = LoggerFactory
			.getLogger(CommitteeEventTest.class);

	@Test
	public void testGettingCommitteeFromCommitteeEvent() {
                Committee committee = new Committee();
            
                CommitteeEvent committeeEvent = new CommitteeEvent();
                committeeEvent.setCommittee(committee);
                
                assertThat(committee, is(equalTo(committeeEvent.getCommittee())));
        }
}
