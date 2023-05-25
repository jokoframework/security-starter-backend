package io.github.jokoframework.securitystarterbackend.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class WebRestControllerAdvice {
    public class ResponseMsg {
        private String messageError;

        public ResponseMsg(String message) {
            this.messageError = message;
        }
        public String getMessageError() {
            return messageError;
        }

        public void setMessageError(String message) {
            this.messageError = message;
        }
        @Override
        public String toString() {
            return "ResponseMsg [messageError=" + messageError + "]";
        }
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SecurityBackendException.class)
    public ResponseMsg handleSecurityBackendException(SecurityBackendException ex) {
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
        return responseMsg;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseMsg handleException(Exception ex) {
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
        return responseMsg;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public ResponseMsg handleUserException(UserException ex) {
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
        return responseMsg;
    }
}
