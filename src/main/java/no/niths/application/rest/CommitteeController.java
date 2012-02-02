package no.niths.application.rest;

import java.util.List;

import no.niths.application.rest.lists.CommitteeList;
import no.niths.common.AppConstants;
import no.niths.domain.Committee;
import no.niths.services.CommitteeService;

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
@RequestMapping(AppConstants.COMMITTEES)
public class CommitteeController {

    @Autowired
    private CommitteeService service;

    private CommitteeList list = new CommitteeList();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCourse(@RequestBody Committee committee) {
        service.create(committee);
    }

    @RequestMapping(value = {"new/name/{name}/description/{description}","new/name/{name}"},
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCourseWithValues(@PathVariable String name, 
            @PathVariable String description) {
        Committee c = new Committee(name, description);
        
        service.create(c);
    }

    
   //produces = RESTConstants.JSON
    @RequestMapping(
           value = { "?id={id}","{id}" }, 
           method = RequestMethod.GET, 
           headers = "Accept="+ RESTConstants.JSON+", " +RESTConstants.XML
           )
    @ResponseBody
    public Committee getByIdAsJSON(@PathVariable long id) {
        return service.getCommitteeById(id);
    }

    @RequestMapping(value = { "{id}.xml", "id/{id}.xml" },
            method = RequestMethod.GET, 
            produces = RESTConstants.XML)
    @ResponseBody
    public Committee getByIdAsXML(@PathVariable long id) {
        return service.getCommitteeById(id);
    }

    @RequestMapping(value = { "", "all.json" }, method = RequestMethod.GET, produces = RESTConstants.JSON)
    @ResponseBody
    public List<Committee> getAllCoursesAsJSON() {
        return service.getAll();
    }

    @RequestMapping(value = { "all.xml" }, method = RequestMethod.GET, produces = RESTConstants.XML)
    @ResponseBody
    public CommitteeList getAllCoursesAsXML() {
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
    public void deleteCourse(@PathVariable long id) {
        service.delete(id);
    }
}
