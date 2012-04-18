package no.niths.application.rest.school;

import static org.junit.Assert.assertEquals;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.school.interfaces.FadderGroupController;
import no.niths.application.rest.school.interfaces.StudentController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class FadderGroupControllerTest {
	
    private MockHttpServletResponse res;

	@Autowired
	private FadderGroupController fadderController;

	@Autowired 
	private StudentController studentController;

	@Before
    public void setUp() {
        res = new MockHttpServletResponse();
    }

	@Test
	public void testAddAndRemoveLeaders(){
		Student s1 = new Student("mail@nith.com");
		Student s2 = new Student("mail2@nith.no");

		
		FadderGroup g1 = new FadderGroup(123);

		g1.getLeaders().add(s1);
		g1.getLeaders().add(s2);
		fadderController.create(g1, res);
		
		assertEquals(g1, fadderController.getById(g1.getId()));
				
		assertEquals(2, fadderController.getById(g1.getId()).getLeaders().size());
		
		fadderController.removeAllLeaders(g1.getId());
		assertEquals(true, fadderController.getById(g1.getId()).getLeaders().isEmpty());
		
		fadderController.addLeader(g1.getId(), s1.getId());
		fadderController.addLeader(g1.getId(), s2.getId());
		assertEquals(2, fadderController.getById(g1.getId()).getLeaders().size());
		
		fadderController.removeLeader(g1.getId(), s1.getId());
		assertEquals(1, fadderController.getById(g1.getId()).getLeaders().size());
		
		
		fadderController.delete(g1.getId());
		
		studentController.delete(s1.getId());
		studentController.delete(s2.getId());
		int remainingGroups= -1;
		try{
			remainingGroups=fadderController.getAll(null).size();
		}catch(ObjectNotFoundException ex){			
			assertEquals(-1,remainingGroups);
		}
	}
	
	@Test
	public void testAddAndRemoveChildren(){
		Student s1 = new Student("mail@ds.com");
		Student s2 = new Student("mail2@mail.sdm");

		
		FadderGroup g1 = new FadderGroup(123);

		g1.getFadderChildren().add(s1);
		g1.getFadderChildren().add(s2);
		fadderController.create(g1, res);
		
		assertEquals(g1, fadderController.getById(g1.getId()));
				
		assertEquals(2, fadderController.getById(g1.getId()).getFadderChildren().size());
		
		fadderController.removeAllChildren(g1.getId());
		assertEquals(true, fadderController.getById(g1.getId()).getFadderChildren().isEmpty());
		
		fadderController.addChild(g1.getId(), s1.getId());
		fadderController.addChild(g1.getId(), s2.getId());
		assertEquals(2, fadderController.getById(g1.getId()).getFadderChildren().size());
		
		fadderController.removeChild(g1.getId(), s1.getId());
		assertEquals(1, fadderController.getById(g1.getId()).getFadderChildren().size());
		
		
		fadderController.delete(g1.getId());
		studentController.delete(s1.getId());
		studentController.delete(s2.getId());
		
		int remainingGroups= -1;
		try{
			remainingGroups=fadderController.getAll(null).size();
		}catch(ObjectNotFoundException ex){			
			assertEquals(-1,remainingGroups);
		}
	}
	
	@Test
	public void testCRUD(){
		int size = 0;
		try{
			size = fadderController.getAll(null).size();
		}catch(ObjectNotFoundException e){
			//Do nothing
		}
		
		FadderGroup group = new FadderGroup(1337);
		
		fadderController.create(group, res);
		
		assertEquals(size + 1, fadderController.getAll(null).size());
		
		assertEquals(1, fadderController.getAll(group).size());
		
		group.setGroupNumber(1338);
		fadderController.update(group);
		
		assertEquals(new Integer(1338), fadderController.getAll(group).get(0).getGroupNumber());
		
		fadderController.delete(group.getId());
	}
	
}
