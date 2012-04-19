package no.niths.services.school.interfaces;

import no.niths.domain.school.Course;
import no.niths.services.interfaces.GenericService;

public interface CourseService extends GenericService<Course> {

    void addRepresentative(Long courseId, Long studentId);

    void removeRepresentative(Long courseId, Long studentId);

    void addSubject(Long courseId, Long subjectId);

    void removeSubject(Long courseId, Long subjectId);
}