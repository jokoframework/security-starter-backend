package io.github.jokoframework.securitystarterbackend.utils;

import io.github.jokoframework.securitystarterbackend.constants.ApiConstants;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class UserUtils {
    public static void validateEmail(String email) throws UserException {
        if (isEmptyString(email)) {
            throw new UserException(ApiConstants.USER_INVALID, "El correo electrónico es obligatorio");
        }else{
            if(!isValidEmail(email)){
                throw new UserException(ApiConstants.USER_INVALID, "El correo electrónico no es válido");
            }
        }
    }
    //TODO VALIDAR SI PASS CONTIENE COMBINACIÓN DE LETRAS Y NUMEROS
    public static void validatePassword(String pass) throws UserException {
        if (isEmptyString(pass)) {
            throw new UserException(ApiConstants.USER_INVALID, "La contraseña es obligatoria");
        }else{
            if(pass.length() < ApiConstants.MIN_LENGTH_PASS){
                throw new UserException(ApiConstants.USER_INVALID,
                        String.format("La contraseña debe contener al menos %d caracteres" , ApiConstants.MIN_LENGTH_PASS));
            }
        }
    }

    public static boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

    public static Date getCurrentDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat(ApiConstants.DATETIME_FORMAT);
        String currentDateTimeString = formatter.format(new Date());
        try {
            return formatter.parse(currentDateTimeString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isEmptyString(String val) {
        return val == null || val.isBlank();
    }
}