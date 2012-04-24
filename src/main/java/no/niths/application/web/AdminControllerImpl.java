package no.niths.application.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.lists.StudentList;
import no.niths.application.web.interfaces.AdminController;
import no.niths.domain.security.Role;
import no.niths.services.interfaces.RoleService;
import no.niths.services.school.interfaces.StudentService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminControllerImpl implements AdminController {

    private static final String ADMIN = "admin";

    private Logger logger = org.slf4j.LoggerFactory
            .getLogger(AdminControllerImpl.class);

    @Autowired
    private StudentService service;

    @Autowired
    private RoleService roles;

    private List<Role> listOfRoles = new ArrayList<Role>();
    private StudentList students = new StudentList();
    private String query = "";
    private String columnName = "firstName";

    /**
     * {@inheritDoc} <br />
     * Request mapping POST
     */
    @RequestMapping(method = RequestMethod.POST)
    public String updateRoles(
            @RequestParam(value = "studentId") Long studentId,
            @RequestParam(value = "checkedRoles", defaultValue = "") Long[] checkedRoles) {

        service.updateRoles(studentId, checkedRoles);
        return "redirect:?columnName=" + columnName + "&query=" + query;
    }

    /**
     * {@inheritDoc} <br />
     * Path: /delete<br />
     * Request mapping: POST
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "studId") Long studentId) {
        try {
            logger.debug("delete: Student id = " + studentId);
            if (studentId != null) {
                service.hibernateDelete(studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage(), e);
        }

        return "redirect:?columnName=" + columnName + "&query=" + query;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

//    /**
//     * {@inheritDoc} <br />
//     * Request mapping: GET
//     */
//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView getStudents(
//            @RequestParam(value = "columnName", defaultValue = "FIRST") String columnName,
//            @RequestParam(value = "query", required = false, defaultValue = "") String query) {
//        ModelAndView view = new ModelAndView(ADMIN);
//        columnName = validColumName(columnName);
//        String[] temp = convertToUTF(query);
//        query = temp[0];
//        String errorMessage = temp[1];
//
//        logger.debug("Method name: getAllStudents columnName " + columnName
//                + " Query: " + query);
//
//        if (!columnName.equals("FIRST")) {
//            students.clear();
//            if (query.equals("")) {
//                students.addAll(service.getStudentsAndRoles(null));
//            } else {
//                students.addAll(service.getStudentByColumn(columnName, query));
//            }
//
//            getRoles();
//
//            // add students and roles to MaV
//            view.addObject("studentList", students);
//            view.addObject("listOfRoles", listOfRoles);
//            view.addObject("exception", errorMessage);
//
//            setLastQuery(columnName, query);
//        }
//        return view;
//    }

    /**
     * Converts a ISO-8859-1 to UTF-8
     * 
     * @param query
     * @return
     */
    private String[] convertToUTF(String query) {
        String errorMessage = null;
        try {
            byte[] in = query.getBytes("iso-8859-1");
            query = new String(in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            errorMessage = e.getMessage();
        }

        return new String[] { query, errorMessage };
    }

    /**
     * Helper method for getting all roles
     */
    private void getRoles() {
        listOfRoles.clear();
        listOfRoles.addAll(roles.getAll(null));
    }

    /**
     * Helper method for setting variables
     * 
     * @param columnName
     * @param query
     */
    private void setLastQuery(String columnName, String query) {
        this.columnName = columnName;
        this.query = query;
    }

    /**
     * Helper method for validating if the given column name is valid
     * 
     * @param columnName
     * @return
     */
    private String validColumName(String columnName) {
        logger.debug("validColumn: columnName " + columnName);
        if (columnName.equals("lastName") || columnName.equals("email")
                || columnName.equals("FIRST")) {
            return columnName;
        }
        return "firstName";
    }
}
