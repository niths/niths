package no.niths.infrastructure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Exam;
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

    @Autowired
    private ExamRepository repo;

    @Test(expected = IllegalArgumentException.class)
    public void whenInsertNull_persistenceShouldFail() {
        repo.create(null);
    }

    @Test
    public void whenExamIsCreated_ExamShouldBePersisted() {
        int size = repo.getAll(null).size();

        Exam exam = new Exam();
        exam.setExamType("Skriftlig");
        repo.create(exam);

        assertThat(size + 1, is(equalTo(repo.getAll(null).size())));
    }

    @Test
    public void whenGetById_ExamShouldBeReturned() {
        int size = repo.getAll(null).size();

        Exam exam = new Exam();
        exam.setName("Eksamen i Java");
        exam.setExamType("Skriftlig");
        repo.create(exam);

        assertThat(size + 1, is(equalTo(repo.getAll(null).size())));

        Exam result = repo.getById(exam.getId());
        assertThat(result, is(equalTo(exam)));

        result = repo.getById(999L);
        assertThat(result, is(equalTo(null)));
    }

    @Test
    public void whenExamIsUpdated_ExamShouldBeUpdated() {
        int size = repo.getAll(null).size();

        Exam exam = new Exam();
        exam.setName("Eksamen i Java");
        exam.setExamType("Skritlig");
        repo.create(exam);

        assertThat(size + 1, is(equalTo(repo.getAll(null).size())));

        exam.setExamType("Muntlig");
        repo.update(exam);

        assertThat("Muntlig", is(equalTo(repo.getById(exam.getId()).getExamType())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        int size = repo.getAll(null).size();

        Exam examInJava = new Exam();
        examInJava.setName("Eksamen i Java");
        repo.create(examInJava);
        Exam examInAdvancedJava = new Exam();
        examInAdvancedJava.setName("Java 2");
        repo.create(examInAdvancedJava);
        Exam examInEnterpriseJava = new Exam();
        examInEnterpriseJava.setName("Java 3");
        repo.create(examInEnterpriseJava);

        assertThat(size + 3, is(equalTo(repo.getAll(null).size())));

        assertThat(size + 1, is(equalTo(repo.getAll(examInJava).size())));
    }
}
