package no.niths.application.rest.interfaces;

import no.niths.domain.security.Role;
/**
 * Admin controller for handling student roles
 *
 */
public interface RoleController extends GenericRESTController<Role> {
	/**
	 * Adds a role to a student
	 * 
	 * @param studId id of the student
	 * @param roleId id of the role
	 * @throws ObjectNotFoundException if student or role does not exists
	 */
	public void addStudentRole(Long studId, Long roleId);
	
	/**
	 * Removes a role from a student
	 * 
	 * @param studId id of the student
	 * @param roleId id of the role to remove
	 * @throws ObjectNotFoundException if student or role does not exist
	 */
	public void removeStudentRole(Long studId, Long roleId);
	
	/**
	 * Removes all roles from a student
	 * 
	 * @param studId id of the student to remove roles from
	 * @throws ObjectNotFoundException if student does not exist
	 */
	public void removeAllRolesFromStudent(Long studId);

}
