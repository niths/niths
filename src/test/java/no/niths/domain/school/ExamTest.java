package no.niths.domain.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;

import no.niths.domain.school.Exam;
import no.niths.domain.school.constants.ExamType;

import org.junit.Test;

public class ExamTest {
    private static final Long ID = 1L;
    private static final String NAME = "Eksamen i PG110";
    private static final ExamType EXAM_TYPE = ExamType.WRITTEN;
    private static final String ALLOWED_AID = "Ingen";
    private static final GregorianCalendar START_TIME = new GregorianCalendar(2012, 3, 20, 9, 00);
    private static final GregorianCalendar END_TIME = new GregorianCalendar(2012, 3, 20, 12, 00);

    @Test
    public void testShouldGenerateNewExam() {
        Exam exam = new Exam();
        exam.setId(ID);
        exam.setName(NAME);
        exam.setExamType(EXAM_TYPE);
        exam.setAllowedAid(ALLOWED_AID);
        exam.setStartTime(START_TIME);
        exam.setEndTime(END_TIME);

        assertThat(ID, is(equalTo(exam.getId())));
        assertThat(NAME, is(equalTo(exam.getName())));
        assertThat(EXAM_TYPE, is(equalTo(exam.getExamType())));
        assertThat(ALLOWED_AID, is(equalTo(exam.getAllowedAid())));
        assertThat(START_TIME, is(equalTo(exam.getStartTime())));
        assertThat(END_TIME, is(equalTo(exam.getEndTime())));

        assertThat(false, is(equalTo(exam.isEmpty())));
    }

    @Test
    public void testEmptyExamObject() {
        Exam exam = new Exam();

        assertThat(true, is(equalTo(exam.isEmpty())));
    }
}
