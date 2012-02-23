package no.niths.application.rest.interfaces;

import no.niths.domain.security.Role;
/**
 * Admin controller for handling student roles
 *
 */
public interface AdminController extends GenericRESTController<Role> {
	/**
	 * Sets the role of a student
	 * 
	 * @param studId id of the student
	 * @param roleId id of the role
	 * @throws ObjectNotFoundException if student or role does not exists
	 */
	public void setStudentRole(Long studId, Long roleId);
	
	/**
	 * Removes the role from a student
	 * 
	 * @param studId id of the student
	 * @throws ObjectNotFoundException if student does not exist
	 */
	public void removeStudentRole(Long studId);

}
