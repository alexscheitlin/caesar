package ch.scheitlin.alex.teamcity;

// https://confluence.jetbrains.com/display/TCD10/REST+API#RESTAPI-BuildLocator
public enum BuildStatus {
    NORMAL, // finished builds which are not canceled, not failed-to-start, not personal, and on default branch (in branched build configurations)
    SUCCESS,
    FAILURE,
    ERROR
}
