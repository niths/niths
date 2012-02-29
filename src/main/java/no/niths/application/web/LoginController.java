package no.niths.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class LoginController {
 
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView printWelcome() {
 
		return new ModelAndView("login").addObject("message",  "Spring 3 TEst");
 
	}
}
