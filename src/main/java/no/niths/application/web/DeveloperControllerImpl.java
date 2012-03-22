package no.niths.application.web;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.web.interfaces.DeveloperController;
import no.niths.domain.Developer;
import no.niths.services.interfaces.DeveloperService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "adminDeveloperImpl")
@RequestMapping("admin/developer")
public class DeveloperControllerImpl implements DeveloperController {

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(DeveloperControllerImpl.class);

	private static final String DEVELOPERS = "developers";
	private List<Developer> allDevelopers = new ArrayList<Developer>();

	@Autowired
	private DeveloperService devService;

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getDevelopers() {
		logger.debug("Recieved request to see admin/developer");
		ModelAndView view = new ModelAndView(DEVELOPERS);
		setAllDevelopers(devService.getAllWithApps(null));
		
		view.addObject("allDevelopers", allDevelopers);
		return view;
	}

	public List<Developer> getAllDevelopers() {
		return allDevelopers;
	}

	public void setAllDevelopers(List<Developer> allDevelopers) {
		this.allDevelopers = allDevelopers;
	}
}
