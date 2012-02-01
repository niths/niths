package no.niths.application.rest;

import java.util.List;

import no.niths.common.AppConstants;
import no.niths.common.RESTConstants;
import no.niths.domain.Committee;
import no.niths.services.CommitteeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(AppConstants.COMMITTEES)
public class CommitteeController {

	private static final Logger logger = LoggerFactory.getLogger(CommitteeController.class);
	
	@Autowired 
	private CommitteeService service;
	
	
    @RequestMapping(
            value    = {"{id}.json", "id/{id}.json","id/{id}"},
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
	public Committee getByIdAsJSON(@PathVariable long id){
    	Committee c = service.getCommitteeById(id);
    	
    	logger.info(c.getName());
		return c;
	}
    
    @RequestMapping(
            value    = {"{id}.xml", "id/{id}.xml"},
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
	public Committee getByIdAsXML(@PathVariable long id){
		return service.getCommitteeById(id);
	}
    
    @RequestMapping(     
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public List<Committee>  getAllCourses(){
    	return service.getAll();
    }
}
