package no.niths.application.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolationException;

import no.niths.application.rest.exception.BadRequestException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.ExamController;
import no.niths.application.rest.interfaces.SubjectController;
import no.niths.application.rest.location.interfaces.RoomController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Exam;
import no.niths.domain.Subject;
import no.niths.domain.location.Room;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class ExamControllerTest {

    private MockHttpServletResponse res;

    @Autowired
    private ExamController examController;

    @Autowired
    private RoomController roomController;

    @Autowired
    private SubjectController subjectController;

    @Before
    public void setUp() {
        res = new MockHttpServletResponse();
    }

    @Test(expected= BadRequestException.class)
    public void testInsertNullObject_shallThrowException() {
        Exam exam = new Exam("X");
        examController.create(exam, res);
    }

    @Test
    public void testCreateAndDelete() {
        int size = 0;

        try {
            size = examController.getAll(null).size();
        } catch (ObjectNotFoundException exception) {
        }

        Exam exam = new Exam("PG210");
        examController.create(exam, res);

        assertThat(size + 1, is(equalTo(examController.getAll(null).size())));

        examController.hibernateDelete(exam.getId());

        int currentSize = 0;

        try {
            currentSize = examController.getAll(null).size();
        }catch (ObjectNotFoundException exception) {
        }

        assertThat(currentSize, is(equalTo(size)));
    }

    @Test
    public void testCreateAndDeleteOfRooms() {
        Exam exam = new Exam("PG210");
        examController.create(exam, res);

        assertThat(exam, is(equalTo(examController.getById(exam.getId()))));

        Room room = new Room("45");
        Room otherRoom = new Room("84");

        roomController.create(room, res);
        roomController.create(otherRoom, res);

        examController.addRoom(exam.getId(), room.getId());
        examController.addRoom(exam.getId(), otherRoom.getId());

        assertThat(2, is(equalTo(examController.getById(exam.getId()).getRooms().size())));

        examController.removeRoom(exam.getId(), room.getId());

        assertThat(1, is(equalTo(examController.getById(exam.getId()).getRooms().size())));
        assertThat(roomController.getById(otherRoom.getId()).getId(), is(equalTo(examController.getById(exam.getId()).getRooms().get(0).getId())));

        examController.hibernateDelete(exam.getId());
        roomController.hibernateDelete(room.getId());
        roomController.hibernateDelete(otherRoom.getId());
    }

    @Test
    public void testCreateAndDeleteOfSubject() {
        Exam exam = new Exam("Eksamen i PG210");
        examController.create(exam, res);

        assertThat(exam, is(equalTo(examController.getById(exam.getId()))));

        Subject subject = new Subject("Programmering");

        subjectController.create(subject, res);

        examController.addSubject(exam.getId(), subject.getId());

        assertThat(subjectController.getById(subject.getId()), is(equalTo(examController.getById(exam.getId()).getSubject())));

        examController.removeSubject(exam.getId());

        assertThat(examController.getById(exam.getId()).getSubject(), is(nullValue()));

        examController.hibernateDelete(exam.getId());
        subjectController.hibernateDelete(subject.getId());
    }
}
