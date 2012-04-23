package no.niths.domain.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.domain.school.Subject;

import org.junit.Test;

public class SubjectTest {
    private static final Long ID = 1L;
    private static final String NAME = "Hovedprosjekt";
    private static final String SUBJECT_CODE = "PJ600";
    private static final String DESCRIPTION = "Avsluttende prosjekt ved NITH";
    private static final Weekday weekday = Weekday.MONDAY;
    private static final String START_TIME = "08:00";
    private static final String END_TIME = "12:00";

    @Test
    public void testShouldGenerateNewSubject() {
        Subject subject = new Subject();    
        subject.setId(ID);
        subject.setName(NAME);
        subject.setSubjectCode(SUBJECT_CODE);
        subject.setDescription(DESCRIPTION);
        subject.setWeekday(weekday);  
        subject.setStartTime(START_TIME);
        subject.setEndTime(END_TIME);

        assertThat(ID, is(equalTo(subject.getId())));
        assertThat(NAME, is(equalTo(subject.getName())));
        assertThat(SUBJECT_CODE, is(equalTo(subject.getSubjectCode())));
        assertThat(DESCRIPTION, is(equalTo(subject.getDescription())));
        assertThat(weekday, is(equalTo(subject.getWeekday())));
        assertThat(START_TIME, is(equalTo(subject.getStartTime())));
        assertThat(END_TIME, is(equalTo(subject.getEndTime())));
    }
}