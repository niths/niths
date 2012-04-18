package no.niths.services.school;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Locker;
import no.niths.services.school.interfaces.LockerService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LockerServiceImplTest {

    @Autowired
    private LockerService service;

    private int numOfLockers;

    private Locker locker1;
    private Locker locker2;
    private Locker locker3;

    @Before
    public void setUp() {
        locker1 = new Locker("001");
        locker2 = new Locker("002");
        locker3 = new Locker("003");

        service.create(locker1);
        service.create(locker2);
        service.create(locker3);
    }

    @After
    public void tearDown() {
        service.delete(locker1.getId());
        service.delete(locker2.getId());
        service.delete(locker3.getId());
    }

    @Test
    public void testCRUD() {

        // Read
        assertEquals(locker1, service.getAll(locker1).get(0));

        final String newLockerNumber = "004";
        locker1.setLockerNumber(newLockerNumber);
        service.update(locker1);
        assertEquals(
                newLockerNumber,
                service.getById(locker1.getId()).getLockerNumber());

//        // Update
//        String newLockerNumber = "004";
//        locker1.setLockerNumber(newLockerNumber);
//        service.update(locker1);
//        assertEquals(
//                newLockerNumber,
//                service.getById(locker1.getId()).getLockerNumber());
    }
}