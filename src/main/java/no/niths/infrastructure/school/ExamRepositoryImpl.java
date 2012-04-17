package no.niths.infrastructure.school;

import no.niths.domain.school.Exam;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.ExamRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepositoryImpl extends AbstractGenericRepositoryImpl<Exam> implements ExamRepository {

    public ExamRepositoryImpl() {
        super(Exam.class, new Exam());
    }
}
