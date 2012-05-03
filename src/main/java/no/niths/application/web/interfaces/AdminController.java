package no.niths.application.web.interfaces;

import org.springframework.web.servlet.ModelAndView;

/**
 * A administrator control panel for searching 
 * students and giving them roles and the possibility to delete them.
 *
 * For the URL too the admin view add /admin
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface AdminController {

    /**
     * Simple method for showing admin.jsp
     * 
     * @return the view
     */
    ModelAndView showAdminPage();
}