package no.niths.application.rest.interfaces;

import no.niths.domain.signaling.AccessField;

/**
 * Controller for AccessField
 */
public interface AccessFieldController
    extends GenericRESTController<AccessField> {
	void addAccessPoint(long afId, long apId);
	void removeAccessPoint(long afId, long apId);
	
}