package no.niths.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Exam;
import no.niths.domain.Subject;
import no.niths.domain.location.Room;
import no.niths.services.interfaces.ExamService;
import no.niths.services.interfaces.RoomService;
import no.niths.services.interfaces.SubjectService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class ExamServiceTest {

    public static final String NAME = "Eksamen i PG320";
    public static final String CHANGED_NAME = "Eksamen i PJ110";

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private RoomService roomService;

    @Test
    public void testCRUD(){

        int size = examService.getAll(null).size();

        Exam exam = new Exam();
        exam.setName(NAME);
        examService.create(exam);
        assertThat(size + 1, is(equalTo(examService.getAll(null).size())));

        Exam tempExam = examService.getById(exam.getId());
        assertThat(NAME, is(equalTo(tempExam.getName())));

        tempExam.setName(CHANGED_NAME);
        examService.update(tempExam);

        tempExam = examService.getById(exam.getId());
        assertThat(CHANGED_NAME, is(equalTo(tempExam.getName())));

        examService.hibernateDelete(exam.getId());
        assertThat(size, is(equalTo(examService.getAll(null).size())));
    }

    @Test
    public void testRelationsBetweenExamAndSubject(){
        Subject subject = new Subject("PG210");
        subjectService.create(subject);

        Exam exam = new Exam();
        exam.setName(NAME);
        examService.create(exam);

        exam.setSubject(subject);
        examService.update(exam);

        assertThat(subjectService.getById(subject.getId()), is(equalTo(examService.getById(exam.getId()).getSubject())));

        examService.hibernateDelete(exam.getId());
        subjectService.hibernateDelete(subject.getId());
    }

    @Test
    public void testRelationsBetweenExamAndRoom(){
        Room room = new Room("143");
        roomService.create(room);

        Room otherRoom = new Room("181");
        roomService.create(otherRoom);

        Exam exam = new Exam(NAME);
        examService.create(exam);

        exam.getRooms().add(room);
        exam.getRooms().add(otherRoom);
        examService.update(exam);

        assertThat(2, is(equalTo(examService.getById(exam.getId()).getRooms().size())));

        examService.hibernateDelete(exam.getId());
        roomService.hibernateDelete(room.getId());
        roomService.hibernateDelete(otherRoom.getId());
    }
}
