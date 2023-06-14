package io.github.jokoframework.securitystarterbackend.exception;

import io.github.jokoframework.common.errors.BusinessException;
import io.github.jokoframework.securitystarterbackend.constants.ApiConstants;

public class UserException extends BusinessException{

    private static final long serialVersionUID = 7867933577361708811L;
    private String errorCode;
    private String message;

    public UserException(String errorCode, String message) {
        super(errorCode, message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public static UserException notFound(Long userId) {
        return new UserException(ApiConstants.USER_NOT_FOUND,
                String.format("Usuario con id = %d no encontrado" , userId));
    }

    public static UserException userNotFound(String username) {
        return new UserException(ApiConstants.USER_NOT_FOUND,
                String.format("Usuario %s no encontrado" , username));
    }

    public static UserException invalidUserCredentials() {
        return new UserException(ApiConstants.USER_INVALID,
                "Usuario o contraseña inválidos");
    }

    public static UserException userAlreadyExists(String username){
        return new UserException(ApiConstants.USER_EXISTS,
                String.format("Username %s ya existe" , username));
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserException{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
