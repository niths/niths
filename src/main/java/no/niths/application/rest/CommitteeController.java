package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.CommitteeList;
import no.niths.common.AppConstants;
import no.niths.domain.Committee;
import no.niths.services.CommitteeService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.COMMITTEES)
public class CommitteeController implements RESTController<Committee>{

	Logger logger = org.slf4j.LoggerFactory.getLogger(CommitteeController.class);
	
    @Autowired
    private CommitteeService service;

    private CommitteeList committeeList = new CommitteeList();

    /**
     * 
     * @param Committee The committee to be created
     */
    @Override
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Committee committee) {
        service.create(committee);
    }

    @Override
    @RequestMapping(
           value = "{id}",
           method = RequestMethod.GET,
           headers = RESTConstants.ACCEPT_HEADER
           )
    @ResponseBody
    public Committee getById(@PathVariable Long id) {
        Committee c = service.getCommitteeById(id);
        
        if(c == null){
        	logger.info("c is null");
            throw new ObjectNotFoundException("No comittees with id :" + id);
        }
        
        
        return c;
    }


    @Override
    @RequestMapping(
            method = RequestMethod.GET, 
            headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public ArrayList<Committee> getAll(Committee committee,
            HttpEntity<byte[]> request) {

    	logger.info(committee.toString());
    	
    	 final String FIRST =
                 request.getHeaders().getFirst(RESTConstants.ACCEPT);
    	 
        if (committee.isEmpty()) {
            if (FIRST.equals(RESTConstants.JSON)) {
                return (ArrayList<Committee>) service.getAll();
            } else if (FIRST.equals(RESTConstants.XML)) {
                committeeList.setData(service.getAll());
                return committeeList;
            }
        } else {
        	 if (FIRST.equals(RESTConstants.JSON)) {
                 return (ArrayList<Committee>) service.getAll(committee);
             } else if (FIRST.equals(RESTConstants.XML)) {
                 committeeList.setData(service.getAll(committee));
                 return committeeList;
             }
        }

        return null;
    }

   /**
     * 
     * @param Course The Course to update
     */
    @Override
    @RequestMapping(
            value  = {"", "{id}" },
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(
            @RequestBody Committee committee,
            @PathVariable Long id) {

        // If the ID is only provided through the URL.
        if (id != null)
            committee.setId(id);

        service.update(committee);
    }

    /**
     * 
     * @param long The id of the Course to delete
     */
    @Override
    @RequestMapping(
            value  = "{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
