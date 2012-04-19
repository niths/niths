package no.niths.services.school.interfaces;

import java.util.List;

import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.services.interfaces.GenericService;

public interface FadderGroupService extends GenericService<FadderGroup> {

    void addLeader(Long groupId, Long studentId);

    void removeLeader(Long groupId, Long studentId);

    void removeAllLeaders(Long groupId);

    void addChild(Long groupId, Long studentId);

    void removeChild(Long groupId, Long studentId);

    void removeChildren(Long groupId, Long [] studentIds);

    void removeAllChildren(Long groupId);

    /**
     * Returns all students without a fadder group
     * @return list with students
     */
	List<Student> getStudentsNotInAGroup();
}