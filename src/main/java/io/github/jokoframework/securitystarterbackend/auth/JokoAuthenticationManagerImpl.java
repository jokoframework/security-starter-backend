package io.github.jokoframework.securitystarterbackend.auth;

import io.github.jokoframework.security.api.JokoAuthentication;
import io.github.jokoframework.security.api.JokoAuthenticationManager;
import io.github.jokoframework.security.dto.request.AuditSessionRequestDTO;
import io.github.jokoframework.security.dto.request.PrincipalSessionRequestDTO;
import io.github.jokoframework.security.services.IAuditSessionService;
import io.github.jokoframework.securitystarterbackend.constants.ApiConstants;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.entities.UserEntity;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JokoAuthenticationManagerImpl implements JokoAuthenticationManager {
    @Autowired
    private IAuditSessionService auditSessionService;

    @Autowired
    private UserService userService;


    @Override
    public JokoAuthentication authenticate(JokoAuthentication authentication)
            throws AuthenticationException{
        try {
            UserEntity user = userService.validateUser(authentication.getUsername(), authentication.getPassword());
            authentication.setAuthenticated(true);
            auditPrincipalSession(authentication, user);
            return authentication;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    private void auditPrincipalSession(JokoAuthentication request, UserEntity result){
        AuditSessionRequestDTO auditRequest = new AuditSessionRequestDTO();
        PrincipalSessionRequestDTO principalRequest = new PrincipalSessionRequestDTO();
        auditRequest.setRemoteIp((String) request.getCustom("ipAddress"));
        auditRequest.setUserAgent((String) request.getCustom("userAgent"));
        auditRequest.setUserDate((Date) request.getCustom("userDate"));
        principalRequest.setAppId(ApiConstants.AUDIT_APP_DEFAULT_ID);
        principalRequest.setAppDescription(ApiConstants.AUDIT_USER_DESCRIPTION);
        principalRequest.setUserId(String.valueOf(result.getUserId()));
        principalRequest.setUserDescription(ApiConstants.AUDIT_APP_DESCRIPTION);
        auditRequest.setPrincipal(principalRequest);
        auditSessionService.save(auditRequest);
    }
}
