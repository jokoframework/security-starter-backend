package io.github.jokoframework.securitystarterbackend.constants;

import org.apache.logging.log4j.util.Strings;

public enum RolEnum {
    ADMIN("ADMIN"),
    NORMAL("NORMAL"); //EJEMPLO, CUALQUIER USUARIO QUE NO SEA ADMIN
    private String dbCode;

    RolEnum(String dbCode) {
        this.dbCode = dbCode;
    }

    public static RolEnum getFromCode (String dbCode) {
        if(Strings.isBlank(dbCode))return null;
        for (RolEnum e : RolEnum.values()) {
            if(e.dbCode.equalsIgnoreCase(dbCode)) {
                return e;
            }
        }
        return null;
    }
}
