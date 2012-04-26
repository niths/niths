package no.niths.application.web.interfaces;

import org.springframework.web.servlet.ModelAndView;

/**
 * A administrator control panel for searching 
 * students and giving them roles and the possibility to delete them.
 */
public interface AdminController {

    /**
     * Simple method for showing admin.jsp
     * 
     * @return the view
     */
    ModelAndView showAdminPage();
}