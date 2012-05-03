package no.niths.application.rest.signaling.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.signaling.AccessPoint;

/**
 * Controller for AccessPoint
 * has the basic CRUD methods
 *
 * For the URL too get Access points add /accesspoints
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface AccessPointController
    extends GenericRESTController<AccessPoint> {}