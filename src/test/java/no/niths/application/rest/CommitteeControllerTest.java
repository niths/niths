package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import no.niths.application.rest.interfaces.CommitteeController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Committee;

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
@TransactionConfiguration(
        transactionManager = TestAppConfig.TRANSACTION_MANAGER)
public class CommitteeControllerTest {

    @Autowired
    private CommitteeController controller;

    @Test
    public void testCreateAndGetCommittee() {
        int originalCount = controller.getAll(null).size();
        
        Committee firstCommittee = new Committee("qux", "corge");
        controller.create(firstCommittee);

        assertEquals(originalCount + 1, controller.getAll(null).size());

        assertEquals(
                controller.getAll(firstCommittee).get(0).getName(),
                firstCommittee.getName());
    }

    @Test
    public void testDeleteCommittee() {

        // Ensure there is one committee when the next is deleted
        Committee firstCommittee = new Committee("foo", "bar");
        controller.create(firstCommittee);

        final int originalCount = controller.getAll(null).size();

        // Persist a Committee to be deleted
        Committee secondCommittee = new Committee("bar", "baz");
        controller.create(secondCommittee);

        controller.delete(controller.getAll(firstCommittee).get(0).getId());

        assertEquals(originalCount, controller.getAll(null).size());
    }

    public Committee getRandomCommittee() {
        return new Committee("foo", "bar");
    }
}