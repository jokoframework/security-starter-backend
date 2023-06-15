package io.github.jokoframework.securitystarterbackend.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.jokoframework.securitystarterbackend.constants.ApiConstants;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import org.apache.commons.codec.binary.Base64;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public static void validatePassword(String pass) throws UserException {
        if (isEmptyString(pass)) {
            throw new UserException(ApiConstants.USER_INVALID, "La contraseña es obligatoria");
        }
        if(pass.length() < ApiConstants.MIN_LENGTH_PASS){
            throw new UserException(ApiConstants.USER_INVALID,
                    String.format("La contraseña debe contener al menos %d caracteres" , ApiConstants.MIN_LENGTH_PASS));
        }
        if(!isValidPassword(pass)){
            throw new UserException(ApiConstants.USER_INVALID,
                    "La contraseña debe contener una combinación de números y letras (mayúsculas y minúsculas)");
        }
    }

    public static boolean isValidPassword(String pass){
        final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d@\\-_#&*$])[A-Za-z\\d@\\-_#&*$]+$";
        return pass.matches(regex);
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

    public static String jwtPayloadDecoder(String jwt){
        String decodedPayload = null;
        try {
            String[] jwtParts = jwt.split("\\.");
            // Decodificar la carga útil en formato Base64 URL
            byte[] payloadBytes = Base64.decodeBase64(jwtParts[1]);
            decodedPayload = new String(payloadBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedPayload;
    }

    public static List<String> getRolFromJson(String json){
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray rolesArray = jsonObject.getAsJsonObject("joko").getAsJsonArray("roles");
        List<String> roles = new ArrayList<>();
        for (int i = 0; i < rolesArray.size(); i++) {
            roles.add(rolesArray.get(i).getAsString());
        }
        return roles;
    }

    public static String getAccessTokenFromJson(String json){
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String accessToken = jsonObject.get("jti").getAsString();
        return accessToken;
    }
}
