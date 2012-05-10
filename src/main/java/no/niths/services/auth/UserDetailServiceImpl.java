package no.niths.services.auth;

import no.niths.security.RequestHolderDetails;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.auth.interfaces.UserDetailService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * <p>
 * Class responsible for fetching the student, application and
 * the developer holding the HTTP request.
 * </p>
 * <p>
 * Delegates the information to @see {@link AuthenticationService}.
 * The actual verification is handled in @see {@link AuthenticationService}
 * </p>
 * 
 */
@Component
public class UserDetailServiceImpl implements UserDetailService {
    
    Logger logger = org.slf4j.LoggerFactory
            .getLogger(UserDetailServiceImpl.class);

    @Autowired
    private AuthenticationService authService;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String sessionToken)
            throws UsernameNotFoundException {
        
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadStudentBySessionToken(String sessionToken) throws UsernameNotFoundException{
        RequestHolderDetails user = authService.authenticateSessionToken(sessionToken);
        if(user == null){
            throw new UsernameNotFoundException("Could not find user with that sessiontoken");
        }
        logger.debug("Found student in UserDetailService");
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long loadDeveloperIdFromDeveloperKey(String developerKey, String developerToken) throws UsernameNotFoundException{
        Long id = authService.authenticateDeveloperToken(developerToken, developerKey);
        if(id == null){
            throw new UsernameNotFoundException("Could not find a developer with that developer token/key");
        }
        logger.debug("Found developer in UserDetailService");
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long loadApplicationIdFromApplicationKey(String applicationKey, String applicationToken) throws UsernameNotFoundException{
        Long id = authService.authenticateApplicationToken(applicationKey, applicationToken);
        if(id == null){
            throw new UsernameNotFoundException("Could not find a application with that token/key");
        }
        logger.debug("Found application in UserDetailService");
        return id;
    }
    

}
