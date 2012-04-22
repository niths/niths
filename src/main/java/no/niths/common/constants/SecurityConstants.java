package no.niths.common.constants;

public final class SecurityConstants {
    //Session times -- TODO: CHANGE TO: 3600000 --> 1 hour
    public static final long MAX_SESSION_VALID_TIME = 360000; //1 hour -->Max time session is valid from user logging in
    public static final long SESSION_VALID_TIME = 60000; //1 min -->Time session is valid between each request. Above field trumps this.

    //Roles
    public static final String R_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String R_STUDENT = "ROLE_STUDENT";
    public static final String R_ADMIN = "ROLE_ADMIN";
    public static final String R_COMMITTEE_LEADER = "ROLE_COMMITEE_LEADER";
    public static final String R_FADDER_LEADER = "ROLE_FADDER_LEADER";
    public static final String R_SR = "ROLE_SR";
    public static final String R_DEVELOPER = "ROLE_DEVELOPER";
    
    //Authorizes annotations
    public static final String ONLY_SR = "hasRole('ROLE_SR')";
    public static final String ONLY_ADMIN = "hasRole('ROLE_ADMIN')";
    public static final String ADMIN_AND_SR = "hasAnyRole('ROLE_ADMIN', 'ROLE_SR')";
    public static final String ADMIN_AND_SR_AND_STUDENT = "hasAnyRole('ROLE_ADMIN', 'ROLE_SR', 'ROLE_STUDENT')";
    public static final String ADMIN_SR_COMMITTEE_LEADER = "hasAnyRole('ROLE_ADMIN', 'ROLE_SR', 'ROLE_COMMITTEE_LEADER')";
    public static final String ADMIN_SR_FADDER_LEADER = "hasAnyRole('ROLE_ADMIN', 'ROLE_SR', 'ROLE_FADDER_LEADER' )";
    public static final String ALL_LEADERS = "hasAnyRole('ROLE_ADMIN', 'ROLE_SR', 'ROLE_FADDER_LEADER', 'ROLE_COMMITTEE_LEADER' )";

}