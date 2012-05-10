package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional 
public class APIEventRepositoryTest {

    private static final Logger logger = LoggerFactory
            .getLogger(APIEventRepositoryTest.class);
    @Autowired
    private APIEventRepository repo;
    
    @Test
    public void testCRUD() {
        
        int size = repo.getAll(null).size();
        
        APIEvent event = new APIEvent("Title", "description", new GregorianCalendar(2012, 2, 22, 22, 30));
        repo.create(event);
        
        assertEquals(size + 1, repo.getAll(null).size());
        logger.debug(event.toString());
        event.setTitle("xxxx");

        assertEquals("xxxx", repo.getById(event.getId()).getTitle());
        
        repo.delete(event.getId());
        assertEquals(size, repo.getAll(null).size());
        
        APIEvent e1 = new APIEvent();
        APIEvent e2 = new APIEvent();
        APIEvent e3 = new APIEvent();
        repo.create(e1);
        repo.create(e2);
        repo.create(e3);
        
        assertEquals(size + 3, repo.getAll(null).size());
        
    }
    
    @Test
    public void testSome(){
        assertEquals(0, repo.getAll(null).size());
    }
    
}
