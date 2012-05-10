package no.niths.application.web.interfaces;

import org.springframework.web.servlet.ModelAndView;
/**
 * Controller for the developer view
 * 
 * Let admin enable/disable developers and applications
 *
 * For the URL too the developer view add /admin/developer
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
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
    String updateDeveloper(Long developerId, Long[] checkDevs, Long[] checkedApps);

    /**
     * Method too reset developer key
     *
     * Add /reset too the URL
     * and use the POST method
     */
    String resetDeveloperKey(Long developerId);

}
