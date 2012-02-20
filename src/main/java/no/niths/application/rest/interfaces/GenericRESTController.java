package no.niths.application.rest.interfaces;

import java.util.ArrayList;

public interface GenericRESTController<T> {
 
    public void create( T domain) ;
    
    public T getById(Long id); 

    public ArrayList<T> getAll(T domain);

    public void update(T domain);

    public void delete(Long id);
}
