package no.niths.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.services.school.interfaces.FadderGroupService;
import no.niths.services.school.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class FadderGroupServiceTest {


	@Autowired
	private FadderGroupService fadderService;
	
	@Autowired
	private StudentService studService;
	
	@Test
	public void testCRUD(){
		int size = fadderService.getAll(null).size();
		FadderGroup g1 = new FadderGroup();
		g1.setGroupNumber(924);
		fadderService.create(g1);
		assertEquals(size + 1, fadderService.getAll(null).size());
		
		FadderGroup temp = new FadderGroup(924);
		assertEquals(1, fadderService.getAll(temp).size());
		
		g1.setGroupNumber(913);
		fadderService.update(g1);
		assertEquals(0, fadderService.getAll(temp).size());
		temp.setGroupNumber(913);
		assertEquals(1, fadderService.getAll(temp).size());
		
		assertEquals(g1, fadderService.getById(g1.getId()));
		
		fadderService.hibernateDelete(g1.getId());
		assertEquals(size, fadderService.getAll(null).size());
	}
	
	@Test
	public void testLeadersRelationship(){
		int studSize = studService.getAll(null).size();
		FadderGroup g1 = new FadderGroup();
		fadderService.create(g1);
		Student s1 = new Student("ssss@sss.com");
		Student s2 = new Student("sss2@sss.com");
		Student s3 = new Student("sss3@sss.com");
		studService.create(s1);
		studService.create(s2);
		studService.create(s3);
		assertEquals(studSize + 3, studService.getAll(null).size());
		
		FadderGroup fetched = fadderService.getById(g1.getId());
		fetched.getLeaders().add(s1);
		fetched.getLeaders().add(s2);
		fetched.getLeaders().add(s3);
		fadderService.update(fetched);
		
		fetched = fadderService.getById(g1.getId());
		assertEquals(3, fetched.getLeaders().size());
		
		//Remove a leader
		fetched.getLeaders().remove(s1);
		fadderService.update(fetched);
		//Did it get removed?
		fetched = fadderService.getById(g1.getId());
		assertEquals(2, fetched.getLeaders().size());
		//Student should still exist
		assertEquals(studSize + 3, studService.getAll(null).size());
		
		//Delete a student
		studService.hibernateDelete(s2.getId());
		assertEquals(studSize + 2, studService.getAll(null).size());
		//Should be removed from faddergroup
		fetched = fadderService.getById(g1.getId());
		assertEquals(1, fetched.getLeaders().size());
		
		//Delete the faddergroup
		fadderService.hibernateDelete(g1.getId());
		//Students should still exist
		assertEquals(studSize + 2, studService.getAll(null).size());
		
		//Clean
		studService.hibernateDelete(s1.getId());
		studService.hibernateDelete(s3.getId());
		assertEquals(studSize, studService.getAll(null).size());
	}
	
	@Test
	public void testChildrenRelationship(){
		int studSize = studService.getAll(null).size();
		FadderGroup g1 = new FadderGroup();
		fadderService.create(g1);
		Student s1 = new Student("ssss@sss.com");
		Student s2 = new Student("sss2@sss.com");
		Student s3 = new Student("sss3@sss.com");
		studService.create(s1);
		studService.create(s2);
		studService.create(s3);
		assertEquals(studSize + 3, studService.getAll(null).size());
		
		ArrayList<Student> children = new ArrayList<Student>();
		children.add(s1);
		children.add(s2);
		children.add(s3);
		
		FadderGroup fadderGroup = new FadderGroup();
		fadderGroup.setId(g1.getId());
		fadderGroup.setFadderChildren(children);
		fadderService.update(fadderGroup);
		
		FadderGroup fetched = fadderService.getById(g1.getId());
		assertEquals(3, fetched.getFadderChildren().size());
		//Remove a child
		
		
		fadderGroup.getFadderChildren().remove(s1);
		fadderService.update(fadderGroup);
		
		fetched = fadderService.getById(g1.getId());
		assertEquals(2, fetched.getFadderChildren().size());
		//Student should still exist
		assertEquals(studSize + 3, studService.getAll(null).size());
		studService.hibernateDelete(s1.getId());
		//Delete a child
		studService.hibernateDelete(s2.getId());
		assertEquals(studSize + 1, studService.getAll(null).size());
		//Should be removed from group
		fetched = fadderService.getById(g1.getId());
		assertEquals(1, fetched.getFadderChildren().size());
		
		//Delete a group
		fadderService.hibernateDelete(g1.getId());
		//Student should not be deleted
		assertEquals(studSize + 1, studService.getAll(null).size());
		
		studService.hibernateDelete(s3.getId());
		assertEquals(studSize, studService.getAll(null).size());
	}
}
