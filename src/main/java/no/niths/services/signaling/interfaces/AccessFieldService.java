package no.niths.services.signaling.interfaces;

import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for AccessField
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addAccessPoint
 * and removeAccessPoint
 * </p>
 */
public interface AccessFieldService extends GenericService<AccessField> {

    /**
     * Adds a AccessPoint too a AccessField
     *
     * @param afId id for the accessField
     * @param apId id for the accessPoint
     */
	void addAccessPoint(long afId, long apId);

    /**
     * Removes the AccessPoint from the AccessField
     *
     * @param afId id for the accessField
     */
	void removeAccessPoint(long afId);
}