package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.lists.CommitteeList;
import no.niths.common.AppConstants;
import no.niths.domain.Committee;
import no.niths.services.CommitteeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory
            .getLogger(CommitteeController.class);
    
    @Autowired
    private CommitteeService service;

    private CommitteeList list = new CommitteeList();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Committee committee) {
        service.create(committee);
    }

    @RequestMapping(
           value = { "?id={id}","{id}" }, 
           method = RequestMethod.GET, 
           headers = RESTConstants.HEADERS
           )
    @ResponseBody
    public Committee getById( @PathVariable long id) {
        return service.getCommitteeById(id);
    }


    @RequestMapping(
            method = RequestMethod.GET, 
            headers = RESTConstants.HEADERS)
    @ResponseBody
    public ArrayList<Committee> getAll(HttpEntity<byte[]> request) {
      
        String req = request.getHeaders().getFirst(RESTConstants.ACCEPT);

        if(req.equals(RESTConstants.JSON)){     
            return (ArrayList<Committee>) service.getAll();    
        }else if (req.equals(RESTConstants.XML)){
            list.setCommitteeData(service.getAll());
            return list;
        }
        return null;
    }

   /**
     * 
     * @param Course The Course to update
     */
    @RequestMapping(
            value  = {"", "{id}"},
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
    @RequestMapping(
            value  = "{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @Override
    public ArrayList<Committee> getByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
}
