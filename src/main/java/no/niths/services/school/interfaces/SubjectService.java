package no.niths.services.school.interfaces;

import no.niths.domain.school.Subject;
import no.niths.services.interfaces.GenericService;

public interface SubjectService extends GenericService<Subject> {

    void addTutor(Long subjectId, Long studentId);

    void removeTutor(Long subjectId, Long studentId);

    void addRoom(Long subjectId, Long roomId);

    void removeRoom(Long subjectId);
}