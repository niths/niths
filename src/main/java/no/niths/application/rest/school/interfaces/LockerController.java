package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Locker;
/**
 * Controller for locker
 * has the basic CRUD methods
 *
 * For the URL too get Locker add /lockers
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface LockerController extends GenericRESTController<Locker> {}