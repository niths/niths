package no.niths.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubjectTest {
    private static final Long ID = 1L;
    private static final String NAME = "Hovedprosjekt";
    private static final String SUBJECT_CODE = "PJ600";
    private static final String DESCRIPTION = "Avsluttende prosjekt ved NITH";
    private static final String WEEKDAY = "Monday";
    private static final String ROOM_NUMBER = "45";
    private static final String START_TIME = "08:00";
    private static final String END_TIME = "12:00";

    private static final Logger logger = LoggerFactory
                    .getLogger(SubjectTest.class);

    @Test
    public void testShouldGenerateNewSubject() {
        Subject subject = new Subject();    
        subject.setId(ID);
        subject.setName(NAME);
        subject.setSubjectCode(SUBJECT_CODE);
        subject.setDescription(DESCRIPTION);
        subject.setWeekday(WEEKDAY);
        subject.setRoomNumber(ROOM_NUMBER);
        subject.setStartTime(START_TIME);
        subject.setEndTime(END_TIME);

        assertThat(ID, is(equalTo(subject.getId())));
        assertThat(NAME, is(equalTo(subject.getName())));
        assertThat(SUBJECT_CODE, is(equalTo(subject.getSubjectCode())));
        assertThat(DESCRIPTION, is(equalTo(subject.getDescription())));
        assertThat(WEEKDAY, is(equalTo(subject.getWeekday())));
        assertThat(ROOM_NUMBER, is(equalTo(subject.getRoomNumber())));
        assertThat(START_TIME, is(equalTo(subject.getStartTime())));
        assertThat(END_TIME, is(equalTo(subject.getEndTime())));

        assertThat(false, is(equalTo(subject.isEmpty())));
    }
    
    @Test
    public void testEmptySubjectObject() {
        Subject subject = new Subject();

        assertThat(true, is(equalTo(subject.isEmpty())));
    }
}
