package no.niths.application.rest;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface RESTController<T> {
 
    public void create( T domain) ;
    
    public T getById(@PathVariable Long id); 

    public ArrayList<T> getAll(T domain, HttpEntity<byte[]> request);

    public void update(T domain,@PathVariable Long id);

    public void delete(@PathVariable Long id);
}
