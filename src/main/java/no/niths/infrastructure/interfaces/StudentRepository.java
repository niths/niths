package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentRepository {

	Long create(Student domain);
	//Read
	List<Student> getAllStudents();
	
	Student getByIdWithCourseAndCommittee(long sid);
	
	Student getById(long sid);
	
	List<Student> getByNamedCommittee(String committyName);
	
	List<Student> getByStudentsNamedCourse(String courseName);	
	
	List<Student> getByName(String name);
	
	List<Student> getStudentsWithCommittees();
	
	List<Student> getStudentsWithCourses();
	
	List<Student> getStudentsWihtCoursesAndCommittees();
	
	void update(Student domain);
	
	Student delete(Student domain);
	
	void delete(long id);
}
