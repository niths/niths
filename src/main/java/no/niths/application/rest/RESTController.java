package no.niths.application.rest;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;

public interface RESTController<T> {
 
    public void create( T domain) ;
    
    public T getById(Long id); 

    public ArrayList<T> getAll(T domain, HttpEntity<byte[]> request);

    public void update(T domain,Long id);

    public void delete(Long id);
}
