package no.niths.application.rest;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.lists.CommitteeEventList;
import no.niths.domain.CommitteeEvent;
import no.niths.services.CommitteeEventsService;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CommitteeEventsController {

    
    @Autowired
    private CommitteeEventsService service;
    
    private CommitteeEventList list = new CommitteeEventList();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCourse(@RequestBody CommitteeEvent event) {
        service.create(event);
    }

    @RequestMapping(value = {"new/name/{name}/description/{description}/date/yy/{year}/mm/{month}/dd/{dayOfMonth}/hh/{hourOfDay}/min/{minute}"
            ,"new/name/{name}"},
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCourseWithValues(@PathVariable String name, 
            @PathVariable String description,
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int dayOfMonth,
            @PathVariable int hourOfDay,
            @PathVariable int minute
            ) {
        
       CommitteeEvent ce= new CommitteeEvent(-1,name, description, new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute));
        
        service.create(ce);
    }

    @RequestMapping(value = { "{id}.json", "id/{id}.json", "id/{id}" }, method = RequestMethod.GET, produces = RESTConstants.JSON)
    @ResponseBody
    
    public CommitteeEvent getByIdAsJSON(@PathVariable long id) {
        return service.getCommitteeEventsById(id);
    }

    @RequestMapping(value = { "{id}.xml", "id/{id}.xml" }, method = RequestMethod.GET, produces = RESTConstants.XML)
    @ResponseBody
    public CommitteeEvent getByIdAsXML(@PathVariable long id) {
        return service.getCommitteeEventsById(id);
    }

    @RequestMapping(value = { "", "all.json" }, method = RequestMethod.GET, produces = RESTConstants.JSON)
    @ResponseBody
    public List<CommitteeEvent> getAllCoursesAsJSON() {
        return service.getAll();
    }

    @RequestMapping(value = { "all.xml" }, method = RequestMethod.GET, produces = RESTConstants.XML)
    @ResponseBody
    public CommitteeEventList getAllCoursesAsXML() {
        list.setCommitteeData(service.getAll());
        return list;
    }

    /**
     * 
     * @param Course The Course to update
     */
    @RequestMapping(
            value  = {"", "{id}"},
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCourse(
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
    public void deleteCourse(@PathVariable long id) {
        // TODO FIXME
        CommitteeEvent c= new  CommitteeEvent();
        c.setId(id);
        service.delete(c);
    }
}
