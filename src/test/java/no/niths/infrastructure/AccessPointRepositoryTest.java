package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.signaling.interfaces.AccessPointRepository;

import org.hibernate.exception.ConstraintViolationException;
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
@TransactionConfiguration(transactionManager =
    TestAppConfig.TRANSACTION_MANAGER)

public class AccessPointRepositoryTest {

    @Autowired
    private AccessPointRepository repo;

    @Test(expected = ConstraintViolationException.class)
    public void testCreateAccessPointWithDuplicateName() {
        AccessPoint accessPoint1 = new AccessPoint("00:00:00:00:00:00");
        repo.create(accessPoint1);

        AccessPoint accessPoint2 = new AccessPoint("00:00:00:00:00:00");
        repo.create(accessPoint2);
    }

    @Test
    public void testCRUD() {
        String exampleAddress = "00:11:22:33:44:55";

        int size = repo.getAll(null).size();
        AccessPoint accessPoint1 = new AccessPoint(exampleAddress);
        repo.create(accessPoint1);
        assertEquals(size + 1, repo.getAll(null).size());

        AccessPoint accessPoint2 = repo.getById(accessPoint1.getId());
        Long id = accessPoint1.getId();
        assertEquals(id, accessPoint2.getId());

        AccessPoint accessPoint3 = new AccessPoint();
        accessPoint3.setAddress(exampleAddress);
        assertEquals(
                accessPoint1.getAddress(),
                repo.getAll(accessPoint3).get(0).getAddress());

        assertTrue(repo.delete(id));
        assertEquals(size, repo.getAll(null).size());
        assertEquals(0, repo.getAll(accessPoint3).size());
    }
}