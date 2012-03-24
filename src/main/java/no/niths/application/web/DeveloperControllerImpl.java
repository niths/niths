package no.niths.application.web;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.web.interfaces.DeveloperController;
import no.niths.common.AppConstants;
import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.services.interfaces.DeveloperService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * Controller for the developer view
 * 
 * Let admin enable/desable developers and applications
 *
 */
@Controller(value = "adminDeveloperImpl")
@RequestMapping(AppConstants.ADMIN_DEV)
public class DeveloperControllerImpl implements DeveloperController {

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(DeveloperControllerImpl.class);

	private static final String DEVELOPERS = "developers";
	private List<Developer> allDevelopers = new ArrayList<Developer>();

	@Autowired
	private DeveloperService devService;

	/**
	 * Renders a view of developers and their apps
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getDevelopers() {
		logger.debug("Recieved request to see admin/developer");
		ModelAndView view = new ModelAndView(DEVELOPERS);
		setAllDevelopers(devService.getAllWithApps(null));
		
		view.addObject("allDevelopers", allDevelopers);
		return view;
	}
	
	/**
	 * Handles POST from the developer page
	 * 
	 * Responsible for setting developers and apps enabled
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public String updateDeveloper(@RequestParam(value="developerId") Long developerId,
										@RequestParam(value = "devs", defaultValue = "") Long[] checkedDevs,
										@RequestParam(value = "apps", defaultValue = "") Long[] checkedApps){
		logger.debug("Updating developer");

		if(developerId != null){
			Developer dev = devService.getById(developerId);
			if(dev != null){
				if(checkedDevs.length == 1){
					dev.setEnabled(true);
				} else {
					dev.setEnabled(false);
				}
				
				for (Application a: dev.getApps()){
					boolean checked = false;
					for (int i = 0; i < checkedApps.length; i++){
						if (a.getId() == checkedApps[i]){
							checked = true;
							break;
						}
					}
					if (checked){
						a.setEnabled(true);
					}else{
						a.setEnabled(false);
					}
				}
				
				devService.updateForDeveloperController(dev);
			}		
		}

		return "redirect:developer";//getDevelopers();
	}

	public List<Developer> getAllDevelopers() {
		return allDevelopers;
	}

	public void setAllDevelopers(List<Developer> allDevelopers) {
		this.allDevelopers = allDevelopers;
	}
}
