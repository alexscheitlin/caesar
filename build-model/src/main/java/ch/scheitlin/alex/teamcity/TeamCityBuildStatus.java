package ch.scheitlin.alex.teamcity;

/**
 * <p>Represents the status of a team city build.</p>
 *
 * <p>Reference: https://confluence.jetbrains.com/display/TCD18/REST+API#RESTAPI-BuildLocator</p>
 */
public enum TeamCityBuildStatus {
    NORMAL, // finished builds which are not canceled, not failed-to-start, not personal, and on default branch (in branched build configurations)
    SUCCESS,
    FAILURE,
    ERROR
}
