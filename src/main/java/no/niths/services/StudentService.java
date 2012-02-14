package no.niths.services;

import java.util.ArrayList;
import java.util.List;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StudentService.class);

	@Autowired
	private StudentRepository repo;

	public void createStudent(Student student) {
		repo.create(student);
	}

	public Student getStudentById(long id) {

		Student s = repo.getById(id);

		if (s != null) {
			s.getCommittees().size();
			s.getCourses().size();
		}

		return s;
	}
	
	
//	/**
//	 * 
//	 * Returns all student with matching name.
//	 * Splits the parameter into two pieces, first name and last name:
//	 * 		
//	 * 		Ex:
//	 * 		Parameter name = "John Doe"
//	 * 		First name = John
//	 * 		Last name = Doe 
//	 * 		
//	 * 		Parameter name = "John J. Doe"
//	 * 		First name = John
//	 * 		Last name = J. Doe 
//	 * 
//	 * @param name The String containing the name
//	 * @return List of all student matching the name
//	 */
//	public List<Student> getStudentByName(String name) {
//		String[] fullName = name.trim().split(" ");
//		Student stud = new Student();
//		if (fullName.length > 1) {
//			for (int i = 1; i < fullName.length; i++) {
//				if (i != 1){
//					fullName[i] += " ";
//				}
//				stud.setLastName(stud.getLastName() + fullName[i]);	
//			}
//		}
//		stud.setFirstName(fullName[0]);
//		logger.info("Search for students with name: " + stud.toString());
//		return getAllStudents(stud);
//	}

	public List<Student> getAllStudents() {
		ArrayList<Student> temp = (ArrayList<Student>) repo.getAllStudents();
		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).setCommittees(null);
			temp.get(i).setCourses(null);
		}
		return temp;
	}

	public List<Student> getAllStudents(Student s) {
		ArrayList<Student> temp = (ArrayList<Student>) repo.getAllStudents(s);
		for (int i = 0; i < temp.size(); i++) {
//			temp.get(i).getCommittees().size();
//			temp.get(i).getCourses().size();
			temp.get(i).setCommittees(null);
			temp.get(i).setCourses(null);
		}
		return temp;
	}

	public void updateStudent(Student student) {
		repo.update(student);
	}

	public boolean deleteStudent(long id) {
		return repo.delete(id);
	}
}
