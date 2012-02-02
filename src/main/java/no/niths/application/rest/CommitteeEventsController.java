package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.lists.CommitteeEventList;
import no.niths.domain.CommitteeEvent;
import no.niths.services.CommitteeEventsService;

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
@RequestMapping("events")
public class CommitteeEventsController implements RESTController<CommitteeEvent> {
 
    @Autowired
    private CommitteeEventsService service;
    
    private CommitteeEventList list = new CommitteeEventList();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody CommitteeEvent event) {
        service.create(event);
    }

    @RequestMapping(value = { "?id={id}","{id}" },
            method = RequestMethod.GET,
            headers = RESTConstants.HEADERS)
    @ResponseBody
    public CommitteeEvent getById(@PathVariable long id) {
        return service.getCommitteeEventsById(id);
    }

    @RequestMapping(method = RequestMethod.GET, headers = RESTConstants.HEADERS)
    @ResponseBody
    public ArrayList<CommitteeEvent> getAll(HttpEntity<byte[]> request) {
        
        String req = request.getHeaders().getFirst(RESTConstants.ACCEPT);

        if(req.equals(RESTConstants.JSON)){     
            return (ArrayList<CommitteeEvent>) service.getAll();    
        }else if (req.equals(RESTConstants.XML)){
            list.setEventData(service.getAll());
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
            @RequestBody CommitteeEvent event,
            @PathVariable Long id) {

        // If the ID is only provided through the URL.
        if (id != null)
            event.setId(id);

        service.update(event);
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
}
