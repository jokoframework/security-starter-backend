package io.github.jokoframework.securitystarterbackend.auth;

import io.github.jokoframework.security.api.JokoAuthentication;
import io.github.jokoframework.security.api.JokoAuthenticationManager;
import io.github.jokoframework.security.dto.request.AuditSessionRequestDTO;
import io.github.jokoframework.security.dto.request.PrincipalSessionRequestDTO;
import io.github.jokoframework.security.services.IAuditSessionService;
import io.github.jokoframework.securitystarterbackend.constants.RolEnum;
import io.github.jokoframework.securitystarterbackend.entities.UserEntity;
import io.github.jokoframework.securitystarterbackend.entities.UserRolEntity;
import io.github.jokoframework.securitystarterbackend.security.CustomAuthenticationDetails;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;

@Component
public class JokoAuthenticationManagerImpl implements JokoAuthenticationManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(JokoAuthenticationManagerImpl.class);

    public static final String AUDIT_APP_DESCRIPTION = "user_id";
    public static final String AUDIT_USER_DESCRIPTION = "joko_starter_backend";
    public static final String AUDIT_APP_DEFAULT_ID = "1";
    @Autowired
    private IAuditSessionService auditSessionService;

    @Autowired
    private UserService userService;

    @Override
    public JokoAuthentication authenticate(JokoAuthentication authentication)
            throws AuthenticationException{
        try {
            LOGGER.info("Objeto recibido para la autenticación: " + authentication);
            UserEntity user = userService.validateUser(authentication.getUsername(), authentication.getPassword());
            userService.setLastAccess(user);
            authentication.setAuthenticated(true);
            addRoles(authentication, user);
            auditPrincipalSession(authentication, user);
            return authentication;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new RuntimeException(error);
        }
    }

    private void addRoles(JokoAuthentication authentication, UserEntity user) {
        List<UserRolEntity> roles = user.getRoles();

        if(roles == null || roles.isEmpty()) {
            LOGGER.error("No tiene roles");
        } else {
            for (UserRolEntity rol : roles) {
                RolEnum rolEnum = RolEnum.getFromCode(rol.getRol().getDescription());
                authentication.addRole(rolEnum.name());
            }
        }
    }

    private void auditPrincipalSession(JokoAuthentication request, UserEntity result){
        AuditSessionRequestDTO auditRequest = new AuditSessionRequestDTO();
        PrincipalSessionRequestDTO principalRequest = new PrincipalSessionRequestDTO();
        auditRequest.setRemoteIp((String) request.getCustom(CustomAuthenticationDetails.IP_ADDRESS));
        auditRequest.setUserAgent((String) request.getCustom(CustomAuthenticationDetails.USER_AGENT));
        auditRequest.setUserDate((Date) request.getCustom(CustomAuthenticationDetails.USER_DATE));
        principalRequest.setAppId(AUDIT_APP_DEFAULT_ID);
        principalRequest.setAppDescription(AUDIT_USER_DESCRIPTION);
        principalRequest.setUserId(String.valueOf(result.getUserId()));
        principalRequest.setUserDescription(AUDIT_APP_DESCRIPTION);
        auditRequest.setPrincipal(principalRequest);
        auditSessionService.save(auditRequest);
    }
}
