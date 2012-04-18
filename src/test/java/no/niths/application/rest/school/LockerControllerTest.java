package no.niths.application.rest.school;

import static org.junit.Assert.assertEquals;
import no.niths.application.rest.school.interfaces.LockerController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Locker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LockerControllerTest {

    private MockHttpServletResponse res;

    private Locker locker1, locker2, locker3;

    @Autowired
    private LockerController controller;

    @Before
    public void setUp() {
        res = new MockHttpServletResponse();

        locker1 = new Locker("001");
        locker2 = new Locker("002");
        locker3 = new Locker("003");
        controller.create(locker1, res);
        controller.create(locker2, res);
        controller.create(locker3, res);
    }

    @After
    public void tearDown() {
        controller.delete(locker1.getId());
        controller.delete(locker2.getId());
        controller.delete(locker3.getId());
    }

    @Test
    public void testGetShouldReturnAppropriateValues() {
        assertEquals(3, controller.getAll(null).size());

        assertEquals(locker1, controller.getAll(locker1).get(0));
    }

    @Test
    public void testCreateShouldGetCreated() {
        Locker locker = new Locker("004");
        controller.create(locker, res);

        final Long ID = locker.getId();
        assertEquals(locker, controller.getById(ID));
        controller.delete(ID);
    }
}