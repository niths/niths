package no.niths.services;

import java.util.ArrayList;
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

    public List<Student> getStudentByName(String name) {
        return repo.getByName(name);
    }

    public List<Student> getAllStudents() {
    	ArrayList<Student> temp = (ArrayList<Student>) repo.getAllStudents();
    	for (int i = 0; i < temp.size(); i++){
    		temp.get(i).setCommittees(null);
    		temp.get(i).setCourses(null);
    	}
        return temp;
    }
    
    public List<Student> getAllStudents(Student s) {
    	return repo.getAllStudents(s);
    }

    public void updateStudent(Student student) {
        repo.update(student);
    }

    public boolean deleteStudent(long id) {
        return repo.delete(id);
    }
}
