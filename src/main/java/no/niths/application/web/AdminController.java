package no.niths.application.web;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.CommitteeControllerImpl;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.services.interfaces.RoleService;
import no.niths.services.interfaces.StudentService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

	private static final String ADMIN = "admin";

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(CommitteeControllerImpl.class);

	@Autowired
	private StudentService service;

	@Autowired
	private RoleService roles;

	private List<Role> listOfRoles;

	private List<Student> students;

	private List<Role> newRoles = new ArrayList<Role>();

	@RequestMapping(method = RequestMethod.POST)
	public String updateRoles(
			@RequestParam(value = "studentId") Long studentId,
			@RequestParam(value = "checkedRoles", defaultValue = "") Long[] checkedRoles) {
		try {

			newRoles.clear();
			logger.debug("updateRoles");
			logger.debug("student id " + studentId);
			logger.debug("CheckedRoles size " + checkedRoles.length);

			Student student = service.getById(studentId);
			logger.debug("Found student = " + (student != null));
			if (student != null) {
				student.setRoles(null);
				for (int i = 0; i < listOfRoles.size(); i++) {
					for (long roles : checkedRoles) {
						if (listOfRoles.get(i).getId() == roles) {
							newRoles.add(listOfRoles.get(i));
						}
					}
				}
				student.setRoles(newRoles);
				service.update(student);
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		}
		// getAllStudents("");
		return "redirect:admin";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "studId") Long studentId) {
		try {
			logger.debug("delete.-------------------------------------------------------------");
			logger.debug(studentId + " ");

			if (studentId != null) {

				service.hibernateDelete(studentId);
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		}
		
		return "admin";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllStudents(
			@RequestParam(value = "columName", defaultValue = "FIRST") String columName,
			@RequestParam(value = "query", required = false, defaultValue = "") String query) {
		ModelAndView view = new ModelAndView(ADMIN);

		logger.debug("Method name: getAllStudents columnmae " + columName
				+ " Query: " + query);
		if (!columName.equals("FIRST")) {
			if(query.equals("")){
				students = service.getStudentsAndRoles(null);
			}else{
				students = service.getStudentByColumn(columName,query);
			}
	
			for (int i = 0; i < students.size(); i++) {
				students.get(i).setCommittees(null);
				students.get(i).setCourses(null);
			}

			getRolesSetSize();
			view.addObject("studentList", students);
			view.addObject("listOfRoles", listOfRoles);
		}
		return view;
	}

	private void getRolesSetSize() {
		listOfRoles = roles.getAll(null);
	}
}
