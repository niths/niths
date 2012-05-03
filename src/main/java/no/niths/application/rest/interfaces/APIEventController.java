package no.niths.application.rest.interfaces;

import no.niths.domain.APIEvent;
/**
 * Controller for handling API events
 *
 * For the URL too get API events add /apievents
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface APIEventController extends GenericRESTController<APIEvent> {

}
