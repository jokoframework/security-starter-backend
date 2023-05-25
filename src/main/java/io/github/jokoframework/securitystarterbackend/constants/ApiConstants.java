package io.github.jokoframework.securitystarterbackend.constants;

public class ApiConstants {
    public static String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static int MIN_LENGTH_PASS = 12;
    public static final String USER_ERROR = "user.error";
    public static final String USER_NOT_FOUND = USER_ERROR + ".notFound";
    public static final String USER_INVALID = USER_ERROR + ".invalid";
    public static final String USER_EXISTS = USER_ERROR + ".exists";

    public static final String AUDIT_APP_DESCRIPTION = "user_id";
    public static final String AUDIT_USER_DESCRIPTION = "joko_starter_backend";
    public static final String AUDIT_APP_DEFAULT_ID = "1";

}
