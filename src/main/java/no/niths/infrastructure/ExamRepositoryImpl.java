package no.niths.infrastructure;

import no.niths.domain.school.Exam;
import no.niths.infrastructure.interfaces.ExamRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepositoryImpl extends AbstractGenericRepositoryImpl<Exam> implements ExamRepository {

    public ExamRepositoryImpl() {
        super(Exam.class, new Exam());
    }
}
