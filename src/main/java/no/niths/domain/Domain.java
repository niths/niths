package no.niths.domain;

import java.io.Serializable;

/**
 * Interface for all Domain classes
 * <p>
 * All new domains must implement this interface
 * to take advantage of the generic repository,
 * service and controller classes
 * </p>
 *
 */
public interface Domain extends Serializable{

    void setId(Long id);
    Long getId();

}
