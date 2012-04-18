package no.niths.application.rest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.RoleController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.security.Role;

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
public class RoleControllerImplTest {

    private MockHttpServletResponse res;

	@Autowired
	private RoleController controller;

//	@Autowired
//	private StudentController studentController;

	private Role testRole01;
	private Role testRole02;
	private Role testRole03;
	private Role testRole04;

//	private Student testStudent;

	@Before
	public void setUp() throws Exception {
	    res = new MockHttpServletResponse();
		testRole01 = new Role("ROLE_ADMIN");
		testRole02 = new Role("ROLE_STUDENT");
		testRole03 = new Role("ROLE_COMMITTE_LEADER");
		testRole04 = new Role("ROLE_ANO");

		controller.create(testRole01, res);
		controller.create(testRole02, res);
		controller.create(testRole03, res);
		controller.create(testRole04, res);

//		testStudent = new Student("am@nith.no");
//		studentController.create(testStudent, res);

	}

	@After
	public void tearDown() throws Exception {
		controller.delete(testRole01.getId());
		controller.delete(testRole02.getId());
		controller.delete(testRole03.getId());
		controller.delete(testRole04.getId());
//		studentController.hibernateDelete(testStudent.getId());
	}

	@Test
	public void testGetByIdLong() {
		Role role = controller.getById(testRole01.getId());
		assertEquals(testRole01, role);
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetByInvalidIdLong() {
		Role role = controller.getById(new Long(-1));
		assertEquals(testRole01, role);
	}

	@Test
	public void testGetAllRole() {
		List<Role> roles = controller.getAll(new Role());
		assertEquals(4, roles.size());
	}

	@Test
	public void testGetAllRoleWithParams() {
		List<Role> roles = controller.getAll(new Role("ROLE_ADMIN"));
		assertEquals(1, roles.size());
	}

	@Test
	public void testGetAllRoleIntInt() {
		List<Role> roles = controller.getAll(new Role(), 0, 2);
		assertEquals(2, roles.size());
	}

//	@Test
//	public void testAddAndRemovenStudentRole() {
//
//		List<Role> studentRoles = studentController
//				.getById(testStudent.getId()).getRoles();
//
//		controller.addStudentRole(testRole01.getId(), testStudent.getId());
//
//		assertEquals(studentRoles.size() + 1,
//				studentController.getById(testStudent.getId()).getRoles()
//						.size());
//
//		controller.removeStudentRole(testRole01.getId(), testStudent.getId());
//		assertEquals(studentRoles.size(),
//				studentController.getById(testStudent.getId()).getRoles()
//						.size());
//	}
//
//	@Test(expected = DuplicateEntryCollectionException.class)
//	public void testAddSameRoleTwice() {
//		controller.addStudentRole(testRole01.getId(), testStudent.getId());
//		controller.addStudentRole(testRole01.getId(), testStudent.getId());
//	}
//
//	@Test(expected = ObjectNotFoundException.class)
//	public void testRemoveANonExistingRoleFromStudent() {
//		controller.removeStudentRole(new Long(-1), testStudent.getId());
//	}
//
//	@Test
//	public void testRemoveAllRolesFromStudent() {
//		controller.removeAllRolesFromStudent(testStudent.getId());
//		assertEquals(0, studentController.getById(testStudent.getId())
//				.getRoles().size());
//	}
//
//	@Test
//	public void testIsStudentInRole() {
//		controller.isStudentInRole(testStudent.getId(), "ROLE_STUDENT");
//	}
//
//	@Test(expected = NotInCollectionException.class)
//	public void testIsStudentNotInRole() {
//		controller.isStudentInRole(testStudent.getId(), "NOT_EXISTING_ROLE");
//	}

	@Test
	public void testUpdateRole() {
		Role r = new Role();
		r.setId(testRole04.getId());
		r.setRoleName("ROLE_SUPER_ADMIN_BRUKER");
		controller.update(r);
		assertEquals(r.getRoleName(), controller.getById(testRole04.getId()).getRoleName());
	}
	
	@Test(expected=org.hibernate.exception.ConstraintViolationException.class)
	public void testConstraint(){
		Role r1 = new Role("ROLE_ADMIN");
		controller.create(r1, res);
	}
}
