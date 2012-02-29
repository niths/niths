package no.niths.services;

import java.util.List;

import no.niths.domain.Student;
import no.niths.domain.FadderUka;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.services.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository repo;

	public Long create(Student student) {
		return repo.create(student);
	}

	/**
	 * Finds and returns a student with a given id. Returns the student with
	 * courses and committees
	 * 
	 * @param id
	 *            ID of student to find
	 * @return the student
	 */
	public Student getById(long id) {
		Student s = repo.getById(id);
		if (s != null) {
			int committeeSize = s.getCommittees().size();
			if (committeeSize > 0) {
				for (int i = 0; i < committeeSize; i++) {
					s.getCommittees().get(i).setEvents(null);
					s.getCommittees().get(i).setLeaders(null);
				}
			} 
			
			int courseSize = s.getCourses().size();
			if (courseSize > 0) {
				for (int i = 0; i < courseSize; i++) {
					s.getCourses().get(i).setSubjects(null);
				}
			} 
			
			s.getFadderUka().size();
		}
		return s;
	}

	public List<Student> getAll(Student s) {
		return repo.getAll(s);
	}

	public void update(Student student) {
		repo.update(student);
	}

	public boolean delete(long id) {
		return repo.delete(id);
	}

	public List<Student> getStudentsWithNamedCourse(String name) {

		List<Student> temp = repo
				.getStudentsWithNamedCourse(name);
	
		return temp;
	}

	@Override
	public void addStudentToFadderUka(Student student, int groupId) {
		student.getFadderUka().add(new FadderUka(groupId));
		update(student);
	}

	@Override
	public List<Student> getAllStudentsInAFadderUka() {
		return repo.getAllStudentsInAFadderUka();
	}

	@Override
	public List<Student> getAllStudentsInFadderUkaBelongingToAGroup(int groupId) {
		return repo.getAllStudentsInFadderUkaBelongingToAGroup(groupId);
	}

	@Override
	public void removeStudentFromFadderUka(Student student, int groupId) {

		if (!student.getFadderUka().isEmpty()) {
			for (int i = 0; i < student.getFadderUka().size();i++) {
				if (student.getFadderUka().get(i).getGroupId() == groupId) {
					student.getFadderUka().remove(i);
				}
			}
			update(student);
		}
	}

	@Override
	public void removeStudentFromAllOfFadderUka(Student student) {
		if (!student.getFadderUka().isEmpty()) {
			student.setFadderUka(null);
			update(student);
		}
	}
}
