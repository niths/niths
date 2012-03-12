package no.niths.application.web.interfaces;

import org.springframework.web.servlet.ModelAndView;
/**
 * A administrator control panel for searching 
 * students and giving them roles and the possibility to delete them.
 */
public interface AdminController {

	/**
	 * Method for updating students with new roles
	 * @param studentId
	 * @param checkedRoles
	 * @return
	 */
	public String updateRoles(Long studentId, Long[] checkedRoles);

	/**
	 * Method for deleting student by a given id
	 * @param studentId
	 * @return
	 */
	public String delete(Long studentId);

	/**
	 * Method for retrieving students by a given query and columName
	 * @param columnName
	 * @param query
	 * @return
	 */
	public ModelAndView getStudents(String columnName, String query);

}
