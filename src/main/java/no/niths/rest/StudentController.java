package no.niths.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("students")
public class StudentController implements SimpleREST {

    @Override
    public Object getAsJSON(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getAsXML(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @RequestMapping(
            value    = "foo", method = RequestMethod.GET)
    public void add(Object t) {
        System.out.println("Object added!");
    }

    @Override
    public void update(Object t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Object t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    
}
