package no.niths.application.rest.helper;

import java.io.Serializable;
/**
 * Class for use in EventController to specify values to use in a search
 */
public class TagProvider extends TimeDTO implements Serializable {
    
    private static final long serialVersionUID = -1339434700493159630L;
    private String tag;

    public TagProvider(String tag) {
        setTag(tag);
    }
    
    public TagProvider() {
        this(null);
    }
    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
    @Override
    public String toString() {
        return tag;
    }
}