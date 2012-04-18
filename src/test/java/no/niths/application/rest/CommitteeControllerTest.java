package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.school.interfaces.CommitteeController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Committee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class CommitteeControllerTest {

    private MockHttpServletResponse res;

    @Autowired
    private CommitteeController controller;

    @Before
    public void setUp() {
        res = new MockHttpServletResponse();
    }

    @Test
    public void testCreateAndGetCommittee() {
        int size = 0;
        try{
            size = controller.getAll(null).size();
        }catch(ObjectNotFoundException e){
        }

        Committee firstCommittee = new Committee("qux", "corge");
        controller.create(firstCommittee, res);

        assertEquals(size + 1, controller.getAll(null).size());

        assertEquals(
                controller.getAll(firstCommittee).get(0).getName(),
                firstCommittee.getName());
    }

    @Test
    public void testDeleteCommittee() {

        // Ensure there is one committee when the next is deleted
        Committee firstCommittee = new Committee("foo", "bar");
        controller.create(firstCommittee, res);

        final int originalCount = controller.getAll(null).size();

        // Persist a Committee to be deleted
        Committee secondCommittee = new Committee("bar", "baz");
        controller.create(secondCommittee, res);

        controller.delete(firstCommittee.getId());

        assertEquals(originalCount, controller.getAll(null).size());
    }

    @Test
    public void testUpdateCommitteeController() {
        Committee firstCommittee = new Committee("foo", "bar");
        controller.create(firstCommittee, res);

        firstCommittee.setName("xyzzy");
        controller.update(firstCommittee);

        assertEquals(firstCommittee.getName(),
                controller.getAll(firstCommittee).get(0).getName());
    }

    public Committee getRandomCommittee() {
        return new Committee("foo", "bar");
    }
}