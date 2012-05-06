package no.niths.infrastructure.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Exam;
import no.niths.domain.school.constants.ExamType;
import no.niths.infrastructure.school.interfaces.ExamRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class ExamRepositoryTest {

    private final String EXAM_NAME   = "Javaeksamen";
    private final ExamType EXAM_TYPE = ExamType.WRITTEN;

    @Autowired
    private ExamRepository repo;

    @Test(expected = IllegalArgumentException.class)
    public void whenInsertNull_persistenceShouldFail() {
        repo.create(null);
    }

    @Test
    public void whenExamIsCreated_ExamShouldBePersisted() {
        final int SIZE = repo.getAll(null).size();

        Exam exam = new Exam();
        exam.setExamType(EXAM_TYPE);
        repo.create(exam);

        assertThat(SIZE + 1, is(equalTo(repo.getAll(null).size())));
    }

    @Test
    public void whenGetById_ExamShouldBeReturned() {
        Exam exam = new Exam(EXAM_NAME, EXAM_TYPE);
        repo.create(exam);

        // Find the newly created exam
        Exam result = repo.getById(exam.getId());
        assertThat(result, is(equalTo(exam)));

        // Try to find an exam that doesn't exist
        result = repo.getById(999L);
        assertThat(result, is(equalTo(null)));
    }

    @Test
    public void whenExamIsUpdated_ExamShouldBeUpdated() {
        final int SIZE = repo.getAll(null).size();

        Exam exam = new Exam(EXAM_NAME, EXAM_TYPE);
        repo.create(exam);

        assertThat(SIZE + 1, is(equalTo(repo.getAll(null).size())));

        exam.setExamType(ExamType.ORAL);
        repo.update(exam);

        assertThat(
                ExamType.ORAL,
                is(equalTo(repo.getById(exam.getId()).getExamType())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        final int SIZE = repo.getAll(null).size();

        Exam javaExam = new Exam("Javaeksamen 1");
        repo.create(javaExam);

        repo.create(new Exam("Javaeksamen 2"));

        repo.create(new Exam("Javaeksamen 3"));

        assertThat(SIZE + 3, is(equalTo(repo.getAll(null).size())));
        assertThat(SIZE + 1, is(equalTo(repo.getAll(javaExam).size())));
    }
}