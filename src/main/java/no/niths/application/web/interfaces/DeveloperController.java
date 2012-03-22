package no.niths.application.web.interfaces;

import org.springframework.web.servlet.ModelAndView;
/**
 * Controller for the developer view
 * 
 * Let admin enable/desable developers and applications
 *
 */
public interface DeveloperController {
	/**
	 * Renders a view of developers and their apps
	 */
	ModelAndView getDevelopers();
	/**
	 * Handles POST from the developer page
	 * 
	 * Responsible for setting developers and apps enabled
	 */
	ModelAndView updateDeveloper(Long developerId, Long[] checkDevs, Long[] checkedApps);

}
