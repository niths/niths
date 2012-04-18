package no.niths.infrastructure.school;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Locker;
import no.niths.infrastructure.school.interfaces.LockerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",
        defaultRollback=true)
public class LockerRepositoryTest {

    private final Long ID = 1L;
    private final String lockerName = "001"; 

    private int size;

    @Autowired
    private LockerRepository repo;

    @Before
    public void setUp() {
        size = repo.getAll(null).size();
    }

    @After
    public void tearDown() {
        repo.delete(ID);
    }

    @Test
    public void testCreateAndPersistLocker() {
        Locker locker = new Locker("001");
        repo.create(locker);

        assertEquals(locker, repo.getAll(locker).get(0));
    }

    @Test
    public void testGetByIdShouldReturnLocker() {
        Locker locker= new Locker("002");
        repo.create(locker);

        assertEquals(
                locker,
                repo.getById(repo.getAll(locker).get(0).getId()));
    }

    @Test
    public void testUpdateLockerShouldBeUpdated() {
        Locker locker = new Locker("003");
        repo.create(locker);
        Long lockerId = repo.getAll(null).get(0).getId();

        locker.setLockerNumber("004");

        assertEquals("004", repo.getById(lockerId).getLockerNumber());
    }

    @Test
    public void testDeleteLocker() {
        Locker locker1 = new Locker("005");
        repo.delete(repo.create(locker1));

        assertEquals(true, repo.getAll(null).isEmpty());
    }
}