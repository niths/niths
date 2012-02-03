package no.niths.services;

import java.util.List;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public void createStudent(Student student) {
        repo.create(student);
    }

    public Student getStudentById(long id) {
    	
    	Student  s = repo.getById(id);
    	
    	if(s != null){
    		s.getCommittees().size();
    		s.getCourses().size();
    	}
    	
        return s;
    }

    public Student getStudentByName(String name) {

        // TODO   // Implement this
     

        return null;
    }

    public List<Student> getAllStudents() {
        return repo.getAllStudents();
    }

    public void updateStudent(Student student) {
        repo.update(student);
    }

    public boolean deleteStudent(long id) {
        return repo.delete(id);
    }
}
