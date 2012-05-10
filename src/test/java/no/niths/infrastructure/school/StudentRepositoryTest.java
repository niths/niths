package no.niths.infrastructure.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Course;
import no.niths.domain.school.Role;
import no.niths.domain.school.Student;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.school.interfaces.CourseRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback=true) 
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private CommitteeRepositorty comRepo;

    /**
     * Testing of basic CRUD functions
     */
    @Test
    public void testCRUD() {
        int size = studentRepo.getAll(null).size();
        Student stud = new Student("John", "Doe");
        stud.setEmail("mail@mail.com");
        studentRepo.create(stud);

        assertEquals(stud, studentRepo.getById(stud.getId()));
        assertEquals(size + 1, studentRepo.getAll(null).size());

        stud.setFirstName("Jane");
        studentRepo.update(stud);

        assertEquals("Jane", studentRepo.getById(stud.getId()).getFirstName());

        studentRepo.delete(stud.getId());
        // assertEquals(null, studentRepo.getById(stud.getId()));

        assertEquals(size, studentRepo.getAll(null).size());

    }

    /**
     * Tests the course association
     */
    @Test
    public void testStudentWithCourses() {
        int cSize = courseRepo.getAll(null).size();
        int comSize = comRepo.getAll(null).size();
        
        Course c1 = new Course("PROG", "casd1");
        Course c2 = new Course("DESIGN", "cdda2");

        courseRepo.create(c1);
        courseRepo.create(c2);
        // Courses should be persisted
        assertEquals(cSize + 2, courseRepo.getAll(null).size());

        Student stud = new Student("John", "Doe");
        stud.setEmail("mail@mail.com");
        stud.getCourses().add(c1);
        stud.getCourses().add(c2);

        studentRepo.create(stud);
        assertEquals(stud, studentRepo.getById(stud.getId()));
        // Test if student has courses
        assertEquals(2, studentRepo.getById(stud.getId()).getCourses().size());

        studentRepo.getById(stud.getId()).getCourses().remove(1);
        assertEquals(1, studentRepo.getById(stud.getId()).getCourses().size());

        assertEquals(cSize + 2, courseRepo.getAll(null).size());

        Committee c = new Committee("Utvalg", "desc");

        comRepo.create(c);
        assertEquals(comSize + 1, comRepo.getAll(null).size());
        stud = studentRepo.getById(stud.getId());
        stud.getCommittees().add(c);

        studentRepo.update(stud);

        assertEquals(1, studentRepo.getById(stud.getId()).getCommittees()
                .size());

    }

    
    @Test
    public void testGetAllStudentsWithParameter_shouldReturnListOfStudentsMatching() {
        
        int size = studentRepo.getAll(null).size();
        createStudentHelper();
        assertEquals(size + 4, studentRepo.getAll(null).size());

        Student toFind = new Student("John", "Doe");
        assertEquals(2, studentRepo.getAll(toFind).size());

        toFind = new Student("Jane", "Doe");
        assertEquals(1, studentRepo.getAll(toFind).size());

        toFind = new Student("XXX", "Doe");
        assertEquals(0, studentRepo.getAll(toFind).size());
    
    }


    @Test
    public void testGetStudentsWithNamedCourse(){
        int size = studentRepo.getAll(null).size();
        List<Student> students =createStudentHelper();
        assertEquals(size + 4, studentRepo.getAll(null).size());
        
        String name ="prog";
        Course c = new Course(name, "ProgProg");
        courseRepo.create(c);
        
        ArrayList<Course> cs = new ArrayList<Course>();
        cs.add(c);
        
        for (int i = 0; i < students.size()-1; i++) {
            students.get(i).setCourses(cs);
            studentRepo.update(students.get(i));
        }
        
        students.clear();
        students = studentRepo.getStudentsWithNamedCourse(name);
        
        assertEquals(3, students.size());
        
    }
    
    private ArrayList<Student>createStudentHelper(){
        ArrayList<Student> students = new ArrayList<Student>();
        
        String [] firstName = {"John","John","Jane","Foo"};
        String [] lastName = {"Doe","Doe","Doe","Bar"};
        
        for (int i = 0; i < lastName.length; i++) {
            Student s1 = new Student(firstName[i],lastName[i]);
            s1.setEmail("mail" + i + "@mail.com");
            studentRepo.create(s1);
            students.add(s1);
        }
        return students;
    }
    
    
    
    @Test
    public void testAddRoles(){
        Student s1 = new Student("John","Doe");
        s1.setEmail("mailai@mail.com");
        studentRepo.create(s1);
    
        s1.getRoles().clear();
        s1.getRoles().add(new Role("ROLE_AMDIN"));
        s1.getRoles().add(new Role("ROLE_AMDINS"));
        
        studentRepo.update(s1);
        
        s1 = studentRepo.getById(s1.getId());
        
        assertNotNull(s1);
        
        assertEquals(2, s1.getRoles().size());
    }
}
