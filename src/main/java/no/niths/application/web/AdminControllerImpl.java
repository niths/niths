package no.niths.application.web;

import no.niths.application.web.interfaces.AdminController;
import no.niths.common.constants.AdminConstantNames;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * A administrator control panel for searching
 * students and giving them roles and the possibility to delete them.
 *
 * For the URL too the admin view add /admin
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(AdminConstantNames.ADMIN)
public class AdminControllerImpl implements AdminController {

    /**
     * {@inheritDoc}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        return new ModelAndView();
    }
}