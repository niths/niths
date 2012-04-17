package no.niths.services.school.interfaces;

import no.niths.domain.school.Exam;
import no.niths.services.interfaces.GenericService;

public interface ExamService extends GenericService<Exam> {

    void addRoom(Long examId, Long roomId);

    void removeRoom(Long examId, Long roomId);

    void addSubject(Long examId, Long subjectId);

    void removeSubject(Long examId);
}
