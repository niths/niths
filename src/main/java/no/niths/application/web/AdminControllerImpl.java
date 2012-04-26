package no.niths.application.web;

import no.niths.application.web.interfaces.AdminController;
import no.niths.common.constants.AdminConstantNames;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(AdminConstantNames.ADMIN)
public class AdminControllerImpl implements AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        return new ModelAndView();
    }
}