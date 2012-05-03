package no.niths.application.rest.lists;

import java.util.List;
/**
 * Interface that ListAdapter implements
 */
public interface Exportable<T> {

    void setData(List<T> list);
}