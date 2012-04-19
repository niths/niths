package no.niths.application.rest.misc.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple discoverability class that handles GET requests to the ROOT uri
 * <p>
 * Returns all valid domain in header
 *
 */
public interface DiscoverabilityController {
    String root(HttpServletRequest request, HttpServletResponse response);
}