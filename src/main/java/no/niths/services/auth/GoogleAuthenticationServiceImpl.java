package no.niths.services.auth;

import no.niths.services.auth.interfaces.GoogleAuthenticationService;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Authenticates a user through Google and are able to fetch their profiles
 * 
 */
@Service
public class GoogleAuthenticationServiceImpl
        implements GoogleAuthenticationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String authenticateAndGetEmail(String token)
            throws HttpClientErrorException {
        Google google = new GoogleTemplate(token);
        LegacyGoogleProfile profile = google.userOperations().getUserProfile();
        return profile.getEmail();
 }

    /**
     * {@inheritDoc}
     */
    @Override
    public Google authenticateAndGetGoogleAccount(String token) {
        return new GoogleTemplate(token);
    }
}