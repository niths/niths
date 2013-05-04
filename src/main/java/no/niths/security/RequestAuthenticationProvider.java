package no.niths.security;

import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.domain.development.Application;
import no.niths.domain.development.Developer;
import no.niths.services.auth.interfaces.UserDetailService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Authenticates requests from the @see {@link RequestAuthenticationFilter}
 * <p>
 * This class is responsible for delegating the authentication
 * to the @see {@link UserDetailService}
 * </p>
 * 
 */
public class RequestAuthenticationProvider implements AuthenticationProvider {

    Logger logger = org.slf4j.LoggerFactory
            .getLogger(RequestAuthenticationProvider.class);

    @Autowired
    private UserDetailService userDetailService;

    /**
     * Checks the Authentication object and uses an instance of
     * UserDetailService to verify the request
     * 
     * 
     * @param authentication
     *            the current authentication object
     * @return a new authentication object with details of the authenticated
     *                                 developer, application and user
     * @throws AuthenticationException
     *             when authentication fails
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        logger.debug("Authentication provider handling the authentication object");
        try {

            RequestAuthenticationInfo authInfo = (RequestAuthenticationInfo) authentication;
            // Verified, proceed
                
                //This is the object holding the authenticated user
                RequestHolderDetails userInfo = new RequestHolderDetails();
                
                logger.debug("Provider found Application-token: "
                        + authInfo.getAppToken());
                // We found tokens and keys, they have been authenticated,
                // proceed to check for a session token
                if (authInfo.getSessionToken() != null) {
                    logger.debug("Provider found Session-token: "
                            + authInfo.getSessionToken());

                    // Get a user that holds the student matching the session
                    // token

                    UserDetails details = userDetailService
                            .loadStudentBySessionToken(authInfo
                                    .getSessionToken());
                    userInfo = (RequestHolderDetails) details;
                    //userInfo.setUserName(((RequestHolderDetails) userDetailService.loadStudentBySessionToken(authInfo.getSessionToken())).getUserName());
                }

                authInfo = new RequestAuthenticationInfo(userInfo,
                        userInfo.getAuthorities());
                logger.debug("Authication provider has finished successfully");
                logger.debug("Sending a RequestAuthenticationInfo object back to the request filter");
                return authInfo;


        } catch (ClassCastException cce) {
            logger.warn("Could not cast the authentication object");
            throw new UnvalidTokenException(
                    "Can not cast the authentication object");
        }
    }

    /**
     * Returns true if the authentication class is supported
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (RequestAuthenticationInfo.class.isAssignableFrom(authentication));

    }

    public UserDetailService getUserDetailService() {
        return userDetailService;
    }

    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

}
