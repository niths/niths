package no.niths.services.school;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Event;
import no.niths.domain.school.Student;
import no.niths.services.school.interfaces.CommitteeService;
import no.niths.services.school.interfaces.EventsService;
import no.niths.services.school.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class CommitteeServiceTest {
	
	@Autowired
	private CommitteeService comService;
	
	@Autowired
	private StudentService studService;
	
	@Autowired
	private EventsService eventService;
	
	@Test
	public void testCRUD(){
		//Test create
		int comSize = comService.getAll(null).size();
		Committee c1 = new Committee();
		c1.setName("xxxxx");
		comService.create(c1);
		assertEquals(comSize + 1, comService.getAll(null).size());
		//Test getALL
		Committee temp = new Committee();
		temp.setName("XXXXXXXXXXXXX");
		assertEquals(0, comService.getAll(temp).size());
		temp.setName("xxxxx");
		assertEquals(1, comService.getAll(temp).size());
		//Test update
		c1.setName("sssss");
		comService.update(c1);
		assertEquals(0, comService.getAll(temp).size());
		//Test delete
		comService.hibernateDelete(c1.getId());
		assertEquals(comSize, comService.getAll(null).size());
	}
	
	@Test
	public void testCommitteeLeadersRelation(){
		Committee c1 = new Committee("sUper",null);
		comService.create(c1);
		Student s1 = new Student("qqqq@mail.com");
		Student s2 = new Student("wwww@mail.com");
		Student s3 = new Student("vvvv@mail.com");
		studService.create(s1);
		studService.create(s2);
		studService.create(s3);
		//Add leaders to committee
		c1.getLeaders().add(s1);
		c1.getLeaders().add(s2);
		c1.getLeaders().add(s3);
		comService.update(c1);
		//Committee should have leaders
		Committee copy = comService.getById(c1.getId());
		assertEquals(3, copy.getLeaders().size());
		
		//Remove a leader
		copy.getLeaders().remove(s1);
		comService.update(copy);
		//Leader should be removed
		copy = comService.getById(c1.getId());
		assertEquals(2, copy.getLeaders().size());
		//But not deleted...
		assertEquals(s1, studService.getById(s1.getId()));
		studService.hibernateDelete(s1.getId());
		
		//Delete a student that is a leader
		studService.hibernateDelete(s2.getId());
		//Should be removed from committee
		copy = comService.getById(c1.getId());
		assertEquals(1, copy.getLeaders().size());
		//and removed from db...
		assertEquals(null, studService.getById(s2.getId()));
		
		//Delete the committee
		comService.hibernateDelete(c1.getId());
		Student fetched = studService.getById(s3.getId());
		assertEquals(s3, fetched);
		
		studService.hibernateDelete(s3.getId());
	}
	
	@Test
	public void testEventRelationship(){
		int eventSize = eventService.getAll(null).size();
		
		Committee c1 = new Committee("asd",null);
		comService.create(c1);
		
		Event e1 = new Event();
		e1.setName("qqqq");
		Event e2 = new Event();
		e2.setName("wwww");
		Event e3 = new Event();
		e3.setName("rrrr");
		eventService.create(e1);
		eventService.create(e2);
		eventService.create(e3);
		assertEquals(eventSize + 3, eventService.getAll(null).size() );
		
		
		c1.getEvents().add(e1);
		c1.getEvents().add(e2);
		c1.getEvents().add(e3);
		assertEquals(0, comService.getById(c1.getId()).getEvents().size());
		comService.update(c1);
		assertEquals(3, comService.getById(c1.getId()).getEvents().size());
		
		//Delete a event
		eventService.hibernateDelete(e1.getId());
		assertEquals(eventSize + 2, eventService.getAll(null).size() );
		//Should be removed from committee
		assertEquals(2, comService.getById(c1.getId()).getEvents().size());
		
		//Remove event from committee
		Committee fetched = comService.getById(c1.getId());
		fetched.getEvents().remove(e3);
		comService.update(fetched);
		assertEquals(1, comService.getById(c1.getId()).getEvents().size());
		//But not from events...
		assertEquals(eventSize + 2, eventService.getAll(null).size() );
		
		//Delete the committee
		comService.hibernateDelete(c1.getId());
		//Event should still exist
		assertEquals(e2, eventService.getById(e2.getId()));
		
		eventService.hibernateDelete(e2.getId());
		eventService.hibernateDelete(e3.getId());
		assertEquals(eventSize, eventService.getAll(null).size() );
	}
	
	@Test
	public void testMemberRelations(){
		int studSize = studService.getAll(null).size();
		
		Committee c1 = new Committee();
		comService.create(c1);
		
		Student s1 = new Student("xxxxxxw@gmail.com");
		Student s2 = new Student("xxxxxxw2@gmail.com");
		Student s3 = new Student("xxxxxxw3@gmail.com");
		studService.create(s1);
		studService.create(s2);
		studService.create(s3);
		
		Committee fetched = comService.getById(c1.getId());
		fetched.getMembers().add(s1);
		fetched.getMembers().add(s2);
		fetched.getMembers().add(s3);
		comService.update(fetched);
		
		fetched = comService.getById(c1.getId());
		assertEquals(3, fetched.getMembers().size());
		
		//Remove member from com
		fetched.getMembers().remove(s1);
		comService.update(fetched);
		//Member should be removed
		fetched = comService.getById(c1.getId());
		assertEquals(2, fetched.getMembers().size());
		//But student should still ex
		assertEquals(studSize + 3,  studService.getAll(null).size());
		studService.hibernateDelete(s1.getId());
		
		//Delete a student
		studService.hibernateDelete(s2.getId());
		//Should be removed from committee
		fetched = comService.getById(c1.getId());
		assertEquals(1, fetched.getMembers().size());
		
		//Delete committee
		comService.hibernateDelete(c1.getId());
		//Student should still exist
		assertEquals(studSize + 1,  studService.getAll(null).size());
		studService.hibernateDelete(s3.getId());
		assertEquals(studSize,  studService.getAll(null).size());
	}
	
	@Test
	public void testAllRelations(){
		Committee c1 = new Committee();
		comService.create(c1);
		
		Student s1 = new Student("mailers@foo.no");
		studService.create(s1);
		
		Event e1 = new Event();
		e1.setName("event-name");
		eventService.create(e1);
		
		Committee temp = comService.getById(c1.getId());
		temp.getEvents().add(e1);
		temp.getMembers().add(s1);
		temp.getLeaders().add(s1);
		
		comService.update(temp);
		
		comService.hibernateDelete(c1.getId());
		studService.hibernateDelete(s1.getId());
		eventService.hibernateDelete(e1.getId());
	}
	
}
