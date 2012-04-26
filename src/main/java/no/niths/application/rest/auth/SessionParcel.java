package no.niths.application.rest.auth;

import no.niths.domain.school.Student;
import no.niths.security.SessionToken;

/**
 * 
 * Wrapper class used in the login process
 * <p>
 * An instace of this class is returned
 * when a Student logs in
 * </p>
 * 
 */
public class SessionParcel {

    private Student authenticatedStudent;

    private SessionToken sessionToken;

    public SessionParcel(
            Student authenticatedStudent,
            SessionToken sessionToken) {
        this.authenticatedStudent = authenticatedStudent;
        this.sessionToken         = sessionToken;
    }

    public Student getAuthenticatedStudent() {
        return authenticatedStudent;
    }

    public void setAuthenticatedStudent(Student authenticatedStudent) {
        this.authenticatedStudent = authenticatedStudent;
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
    }
}