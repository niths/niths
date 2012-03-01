package no.niths.application.web;

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
	private StudentService serice;
	
	@Autowired
	private RoleService roles;
	
	private List<Role> listOfRoles;
	

	@RequestMapping(method =RequestMethod.POST)
	public ModelAndView updateRoles(){
		ModelAndView view = new ModelAndView(ADMIN);
		logger.debug("hello" + " -----------------");
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllStudents(
			@RequestParam(value = "query", required = false, defaultValue="") String query) {
		ModelAndView view = new ModelAndView(ADMIN);
		List<Student> students = null;

	
		
		
		logger.debug(query+ "  _--------------------------------------------------------");

		if (!(query.equals(""))) {
			Student s = new Student();
			s.setFirstName(query);
			students = serice.getStudentsAndRoles(s);
		} else{
			students = serice.getStudentsAndRoles(null);
		}

		getRolesSetSize();
		view.addObject("studentList", students);
		view.addObject("listOfRoles", listOfRoles);
		return view;
	}
	
	private void getRolesSetSize(){
		listOfRoles = roles.getAll(null);
	}
}
