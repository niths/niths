package no.niths.infrastructure;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl extends GenericRepositoryImpl<Student>
		implements StudentRepository {

	public StudentRepositoryImpl() {
		super(Student.class);

	}
}
