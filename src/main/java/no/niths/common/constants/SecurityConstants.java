package no.niths.common.constants;

/**
 * Constants for security
 *
 */
public final class SecurityConstants {
    //Session times -- TODO: CHANGE TO: 3600000 --> 1 hour
    public static final long MAX_SESSION_VALID_TIME = 7200000; //Max time session is valid from user logging in

    public static final long SESSION_VALID_TIME = 36000000; //Time session is valid between each request. Above field trumps this.

    //Roles
    public static final String R_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String R_STUDENT = "ROLE_STUDENT";
    public static final String R_ADMIN = "ROLE_ADMIN";
    public static final String R_COMMITTEE_LEADER = "ROLE_COMMITEE_LEADER";
    public static final String R_FADDER_LEADER = "ROLE_FADDER_LEADER";
    public static final String R_SR = "ROLE_SR";
    public static final String R_DEVELOPER = "ROLE_DEVELOPER";
    public static final String R_LIBRARIAN = "ROLE_LIBRARIAN";
    
    
    //Authorizes annotations
    public static final String ONLY_SR = "hasRole('"+R_SR+"')";
    public static final String ONLY_ADMIN = "hasRole('"+R_ADMIN+"')";
    public static final String ADMIN_AND_SR = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"')";
    public static final String ADMIN_AND_SR_AND_STUDENT = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"', '"+R_STUDENT+"')";
    public static final String ADMIN_AND_SR_STUDENT_AND_LBRARIAN = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"', '"+R_STUDENT+"', '"+R_LIBRARIAN+"')";
    public static final String ADMIN_SR_COMMITTEE_LEADER = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"', '"+R_COMMITTEE_LEADER+"')";
    public static final String ADMIN_SR_LIBRARIAN = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"', '"+R_LIBRARIAN+"')";
    public static final String ADMIN_SR_FADDER_LEADER = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"', '"+R_FADDER_LEADER+"')";
    public static final String ALL_LEADERS = "hasAnyRole('"+R_ADMIN+"', '"+R_SR+"', '"+R_FADDER_LEADER+"' , '"+R_COMMITTEE_LEADER+"')";
  
    

}
