package no.niths.application.rest.lists;

import java.util.ArrayList;
import java.util.List;

public abstract class ListAdapter<E> extends ArrayList<E> implements
        Exportable<E> {

    private static final long serialVersionUID = 3456993319161976498L;

    @Override
    public abstract void setData(List<E> list);

}
