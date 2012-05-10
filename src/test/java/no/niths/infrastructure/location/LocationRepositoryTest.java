package no.niths.infrastructure.location;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.location.Location;
import no.niths.infrastructure.location.interfaces.LocationRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository repo;
    
    @Test
    public void testCRUD() {
        // create
        int size = repo.getAll(null).size();
        
        Location loc = new Location("Oslo",10.2304,90.2030);
    
        repo.create(loc);
        assertEquals(size + 1, repo.getAll(null).size());
        assertEquals(loc, repo.getById(loc.getId()));


        loc.setPlace("Molde");
        repo.update(loc);
        loc = repo.getById(loc.getId());
        assertEquals("Molde", loc.getPlace());
    

        repo.delete(loc.getId());
        assertEquals(size, repo.getAll(null).size());
        
    }

}
