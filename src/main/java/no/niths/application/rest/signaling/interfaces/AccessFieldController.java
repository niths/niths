package no.niths.application.rest.signaling.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.signaling.AccessField;

/**
 * Controller for AccessField
 * has the basic CRUD methods and
 * methods too add and remove an accessPoint
 *
 * For the URL too get Access fields add /accessfields
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface AccessFieldController
    extends GenericRESTController<AccessField> {

    /**
     * Too add accessPoint add /{afId}/accesspoint/{apId}
     * too the URL
     *
     * Use the POST method
     *
     * @param afId id for the accessField
     * @param apId id for the accessPoint
     */
	void addAccessPoint(long afId, long apId);

    /**
     * Too remove accessPoint add /{afId}/accesspoint
     * too the URL
     *
     * Use the DELETE method
     *
     * @param afId id for the accessField
     */
	void removeAccessPoint(long afId);
	
}