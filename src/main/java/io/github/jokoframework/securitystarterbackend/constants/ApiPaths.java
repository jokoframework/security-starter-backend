package io.github.jokoframework.securitystarterbackend.constants;


public class ApiPaths {
    private static final String BASE_API = "/aoc/security/backend";
    public static final String STATUS = BASE_API + "/status";
    public static final String USER = BASE_API + "/user";
    public static final String SIGNUP = "/signup";
    public static final String CREATE_USER = "/create";
    public static final String UPDATE_USER = "/update";
    public static final String DELETE_USER = "/delete";
    public static final String LIST_USERS = "/list";
    public static final String GET_USER = LIST_USERS + "/get";
}
