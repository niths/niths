package no.niths.services.signaling.interfaces;

import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.GenericService;

public interface AccessFieldService extends GenericService<AccessField> {

	void addAccessPoint(long afId, long apId);

	void removeAccessPoint(long afId);
}