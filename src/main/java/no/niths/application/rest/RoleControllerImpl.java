package no.niths.application.rest;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.RoleController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.RoleList;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.RoleService;
import no.niths.services.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for handling roles
 * 
 */
@Controller
@RequestMapping(AppConstants.ROLES)
public class RoleControllerImpl extends AbstractRESTControllerImpl<Role>
		implements RoleController {

	private static final Logger logger = LoggerFactory
			.getLogger(RoleControllerImpl.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private StudentService studentService;

	private RoleList roleList = new RoleList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	@RequestMapping(value = { "add/role/{roleId}/{studentId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Role added")
	public void addStudentRole(@PathVariable Long roleId,
			@PathVariable Long studentId) {
		Student stud = studentService.getStudentWithRoles(studentId);
		validateObject(stud, Student.class.getSimpleName());

		boolean hasRole = false;
		for (Role r : stud.getRoles()) {
			if (r.getId() == roleId) {
				hasRole = true;
				break;
			}
		}

		if (!hasRole) {

			Role role = roleService.getById(roleId);
			validateObject(role, Role.class.getSimpleName());

			stud.getRoles().add(role);
			studentService.update(stud);

			logger.debug("Added role to student: " + role.getRoleName());

		}else{
			throw new DuplicateEntryCollectionException("Student got the provided the role");
		}

	}

	private void validateObject(Object obj, String domain) {
		ValidationHelper.isObjectNull(obj, domain + " dose not exist");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	@RequestMapping(value = { "remove/role/{roleId}/{studentId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Role removed")
	public void removeStudentRole(@PathVariable Long roleId,
			@PathVariable Long studentId) {
		Student stud = studentService.getStudentWithRoles(studentId);
		validateObject(stud, Student.class.getSimpleName());

		boolean hasRole = false;
		for (Role r : stud.getRoles()) {
			if (r.getId() == roleId) {
				stud.getRoles().remove(r);
				studentService.update(stud);
				logger.debug("Removed role from student: " + r.getRoleName());
				hasRole = true;
				break;
			}
		}
		
		if(!hasRole){
			throw new ObjectNotFoundException("Role does not exist");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	@RequestMapping(value = { "remove/roles/{studentId}" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Roles removed from student")
	public void removeAllRolesFromStudent(@PathVariable Long studId) {
		Student stud = studentService.getStudentWithRoles(studId);
		validateObject(stud, Student.class.getSimpleName());

		if (!(stud.getRoles().isEmpty())) {
			stud.getRoles().clear();
			studentService.update(stud);
			logger.debug("All roles removed from student");
		}else{
			logger.debug("Student did not have any roles");
		}
		

	}

	/**
	 * Return 200 ok is student is in role, else 204, no content
	 * 
	 * @param studId
	 * @param roleId
	 */
	@Override
	@RequestMapping(value = { "{studId}/{roleName}" }, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK, reason = "Student has role")
	public void isStudentInRole(@PathVariable Long studId,
			@PathVariable String roleName) {
		Student stud = studentService.getStudentWithRoles(studId);
		validateObject(stud, Student.class.getSimpleName());

		boolean hasRole = false;

		for (Role r : stud.getRoles()) {
			logger.debug(r.getRoleName());
			if (r.getRoleName().equals(roleName)) {
				hasRole = true;
				break;
			}
		}

		if (!hasRole) {
			throw new NotInCollectionException("Student does not have the role");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Role> getService() {
		return roleService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Role> getList() {
		return roleList;
	}
}
