package no.niths.application.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("students")
public class StudentController {

    public Object getAsJSON(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getAsXML(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @RequestMapping(
            value    = "foo", method = RequestMethod.GET)
    public void add(Object t) {
        System.out.println("Object added!");
    }

    public void update(Object t) {
        // TODO Auto-generated method stub
        
    }

    public void delete(Object t) {
        // TODO Auto-generated method stub
        
    }

    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    
}
