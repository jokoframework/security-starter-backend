package io.github.jokoframework.securitystarterbackend.constants;


public class ApiPaths {
    public static final String API_PATTERN = "/api/.*";
    public static final String API_BASE = "/api";
    public static final String API_SECURE = "/api/secure";
    public static final String STATUS = API_BASE + "/status";
    public static final String SIGNUP = "/signup";


    /**
     * routes for user's management
     */
    public static final String USER = API_SECURE + "/user";
    public static final String CREATE_USER = "/create";
    public static final String UPDATE_USER = "/update";
    public static final String DELETE_USER = "/delete";
    public static final String LIST_USERS = "/list";
    public static final String GET_USER = LIST_USERS + "/get";


    /**
     * routes for joko
     */
    public static final String API_SESSIONS = API_BASE + "/sessions";


    private ApiPaths() {

    }
}
