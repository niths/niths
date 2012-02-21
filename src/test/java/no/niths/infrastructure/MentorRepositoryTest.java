package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Mentor;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.MentorRepository;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class MentorRepositoryTest {

	@Autowired
	private MentorRepository mentorRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Test
	public void testAddMentor() {
		Mentor m = new Mentor();
//				("Andre", "lastName", "email@mail.no", "12345678",
//				1);
		Student s = new Student("Knut", "knutsen");
		studentRepo.create(s);
		mentorRepo.create(m);
		List<Mentor> mentors = mentorRepo.getAll(null);

		assertEquals(1, mentors.size());
		Mentor men = m;

		men.setGroupId(3);
		mentorRepo.update(men);
		mentors = mentorRepo.getAll(null);
		assertEquals(3, mentors.get(0).getGroupId());

		List<Student> ss = studentRepo.getAll(null);
		assertEquals(2, ss.size());

	}
}
