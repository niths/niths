package no.niths.infrastructure.school.interfaces;

import java.util.List;

import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;


public interface FadderGroupRepository extends GenericRepository<FadderGroup>{

	FadderGroup getGroupBelongingToStudent(Long studentId);
	
	/**
     * Returns all students without a fadder group
     * @return list with students
     */
	List<Student> getStudentsNotInAGroup();
	
}
