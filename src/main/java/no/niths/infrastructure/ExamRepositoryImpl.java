package no.niths.infrastructure;

import no.niths.domain.Exam;
import no.niths.infrastructure.interfaces.ExamRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepositoryImpl extends AbstractGenericRepositoryImpl<Exam> implements ExamRepository {

    public ExamRepositoryImpl() {
        super(Exam.class);
    }

    @Override
    public void hibernateDelete(long id) {
        Exam exam = new Exam();
        exam.setId(id);
        getSession().getCurrentSession().delete(exam);
    }
}
