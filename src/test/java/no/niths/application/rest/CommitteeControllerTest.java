package no.niths.application.rest;

import static org.junit.Assert.*;
import no.niths.application.rest.interfaces.CommitteeController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Committee;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
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

        Committee secondCommittee = controller.getAll(firstCommittee).get(0);

        assertEquals(secondCommittee.getName(), firstCommittee.getName());
    }

    public Committee getRandomCommittee() {
        return new Committee("foo", "bar");
    }
}