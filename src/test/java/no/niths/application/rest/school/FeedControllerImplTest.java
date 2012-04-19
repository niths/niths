package no.niths.application.rest.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.location.interfaces.LocationController;
import no.niths.application.rest.school.interfaces.FeedController;
import no.niths.application.rest.school.interfaces.StudentController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.location.Location;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Student;

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
public class FeedControllerImplTest {

    private MockHttpServletResponse res;

	@Autowired
	private FeedController controller;

	@Autowired
	private StudentController studentcontroller;

	@Autowired
	private LocationController locationController;

	private Feed testFeed01;
	private Feed testFeed02;
	private Feed testFeed03;
	private Feed testFeed04;
	private Location testLocation;
	private Student testStudents;

	@Before
	public void setUp() throws Exception {
	    res = new MockHttpServletResponse();
		testFeed01 = new Feed("A funny message");
		testFeed02 = new Feed("Paryt beer");
		testFeed03 = new Feed("Not ");
		testFeed04 = new Feed("A funny message 22 ");

		controller.create(testFeed01, res);
		controller.create(testFeed02, res);
		controller.create(testFeed03, res);
		controller.create(testFeed04, res);

		testLocation = new Location("School", 12.123, 12.3122);
		locationController.create(testLocation, res);

		testStudents = new Student("Jhone", "doe", 'M', 1, "doejho09@nith.no",
				"81549300", "This is a super student");
		studentcontroller.create(testStudents, res);
	}

	@After
	public void tearDown() throws Exception {
		controller.delete(testFeed01.getId());
		controller.delete(testFeed02.getId());
		controller.delete(testFeed03.getId());
		controller.delete(testFeed04.getId());
		locationController.delete(testLocation.getId());
		studentcontroller.delete(testStudents.getId());
	}

	@Test
	public final void testGetAllFeed() {
		List<Feed> feeds = controller.getAll(new Feed());
		assertEquals(4, feeds.size());
	}

	@Test
	public final void testGetAllFeedIntInt() {
		ArrayList<Feed> feeds = controller.getAll(new Feed(), 1, 3);
		assertEquals(3, feeds.size());
	}

	@Test
	public final void testGetByIdLong() {
		Feed feed = controller.getById(testFeed03.getId());
		assertEquals(testFeed03, feed);
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testGetByInvalidId_shouldThrowException() {
		controller.getById(new Long(-1));
	}
	
	@Test
	public final void testUpdate() {
		//TODO fix test here! :)
	}

	@Test
	public void testAddAndRemoveLocation() {
		controller.addLocation(testFeed01.getId(), testLocation.getId());
		assertEquals(testLocation, controller.getById(testFeed01.getId())
				.getLocation());

		controller.removeLocation(testFeed01.getId());
		assertNull(controller.getById(testFeed01.getId()).getLocation());
	}

	@Test(expected = ObjectInCollectionException.class)
	public void testAddSameLocationTwice() {
		controller.addLocation(testFeed01.getId(), testLocation.getId());
		controller.addLocation(testFeed01.getId(), testLocation.getId());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testRemoveAFeedsNonExistingLocation() {
		controller.removeLocation(testFeed01.getId());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testRemoveANonExistingFeedAndAExistingLocation() {
		controller.removeLocation(new Long(-1));
	}

	@Test
	public void testAddAndRemoveStudent() {
		controller.addStudent(testFeed01.getId(), testStudents.getId());
		assertEquals(testStudents, controller.getById(testFeed01.getId())
				.getStudent());

		controller.removeStudent(testFeed01.getId());
		assertNull(controller.getById(testFeed01.getId()).getStudent());
	}

	@Test(expected = ObjectInCollectionException.class)
	public void testAddSameStudentTwice() {
		controller.addStudent(testFeed01.getId(), testStudents.getId());
		controller.addStudent(testFeed01.getId(), testStudents.getId());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testRemoveAFeedsNonExistingStudent() {
		controller.removeLocation(testFeed01.getId());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testRemoveANonExistingFeedtAndAExistingStudent() {
		controller.removeLocation(new Long(-1));
	} 
}
