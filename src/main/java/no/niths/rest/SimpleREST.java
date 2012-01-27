package no.niths.rest;

public interface SimpleREST<T> {

    // GET

    public T getAsJSON(String id);
    public T getAsXML(String id);

    // POST

    public void add(T t);

    //PUT

    public void update(T t);

    // DELETE

    public void delete(String id);
    public void delete(T t);
}
