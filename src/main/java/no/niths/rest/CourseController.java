package no.niths.rest;

import no.niths.domain.Course;
import no.niths.services.ICourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("courses")
//@ContextConfiguration({ "/app-config.xml" })
public class CourseController implements SimpleREST<Course>{

    @Autowired
    private ICourseService service;

    /**
     * 
     * @param String the course's id
     * @return The course identified by the id
     */
    @RequestMapping(
            value    = "{id}.json",
            method   = RequestMethod.GET,
            produces = RESTConstants.JSON)
    @ResponseBody
    public Course getAsJSONs(@PathVariable String id) {
        //System.out.println("fooooooooooooo");
        return service.getSome();
        //return service.getCourseById(id);
    }
//    @RequestMapping(
//    		value    = "{id}.json",
//    		method   = RequestMethod.GET,
//    		produces = RESTConstants.JSON)
//    @ResponseBody
//    public Course getAsJSONs(@PathVariable String id) {
//    	//System.out.println("fooooooooooooo");
//    	return new Course();
//    	//return service.getCourseById(id);
//    }

    
    
    @Override
    @RequestMapping(
            value    = "{id}.xml",
            method   = RequestMethod.GET,
            produces = RESTConstants.XML)
    @ResponseBody
    public Course getAsXML(@PathVariable String id) {
        return new Course();
    }

    @Override
    public void add(Course t) {
        // TODO Autfooo-generated method stub
        
    }

    @Override
    public void update(Course t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Course t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }



	@Override
	public Course getAsJSON(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}