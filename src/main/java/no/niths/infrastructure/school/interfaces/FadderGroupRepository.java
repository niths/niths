package no.niths.infrastructure.school.interfaces;

import java.util.List;

import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;

/**
 * Repository class for FadderGroup
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getGroupBelongingToStudent
 * and getStudentsNotInAGroup
 * </p>
 */
public interface FadderGroupRepository extends GenericRepository<FadderGroup>{

    /**
     * Returns the group the student is a child in
     * @param studentId id of the student
     * @return FadderGroup the group the student is a child in
     */
    FadderGroup getGroupBelongingToStudent(Long studentId);
    
    /**
     * Returns all students without a fadder group
     * 
     * @return list with students not in a group
     */
    List<Student> getStudentsNotInAGroup();
    
}
