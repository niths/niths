package no.niths.application.rest.signaling.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.signaling.AccessField;

/**
 * Controller for AccessField
 */
public interface AccessFieldController
    extends GenericRESTController<AccessField> {
	
	void addAccessPoint(long afId, long apId);
	
	void removeAccessPoint(long afId);
	
}