package no.niths.application.web;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.web.interfaces.DeveloperController;
import no.niths.common.constants.AdminConstantNames;
import no.niths.domain.development.Application;
import no.niths.domain.development.Developer;
import no.niths.services.auth.interfaces.KeyGeneratorService;
import no.niths.services.development.interfaces.DeveloperService;
import no.niths.services.interfaces.MailSenderService;

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
 * Let admin enable/disable developers and applications
 *
 * For the URL too the developer view add /admin/developer
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller(value = "adminDeveloperImpl")
@RequestMapping(AdminConstantNames.ADMIN_DEV)
public class DeveloperControllerImpl implements DeveloperController {

    private Logger logger = org.slf4j.LoggerFactory
            .getLogger(DeveloperControllerImpl.class);

    private static final String DEVELOPERS = "developers";
    private List<Developer> allDevelopers = new ArrayList<Developer>();

    @Autowired
    private DeveloperService devService;
    
    @Autowired
    private KeyGeneratorService keyService;
    
    @Autowired
    private MailSenderService mailService;

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetDeveloperKey(@RequestParam(value="developerId") Long developerId){
        logger.debug("Reset developer");
        Developer dev = devService.getById(developerId);
        if(dev != null){
            dev.setDeveloperToken(null);
            dev.setDeveloperKey(keyService.generateDeveloperKey());
            devService.update(dev);
            
            mailService.sendDeveloperRegistratedConfirmation(dev);
        }
        return "redirect:/admin/developer";
    }

    /**
     * {@inheritDoc}
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
                
                devService.update(dev);
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
