package io.github.jokoframework.securitystarterbackend.auth;

import io.github.jokoframework.security.JokoJWTClaims;
import io.github.jokoframework.security.api.JokoAuthorizationManager;
import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;
import io.github.jokoframework.securitystarterbackend.constants.RolEnum;
import io.github.jokoframework.securitystarterbackend.security.CustomAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Optional;

public class JokoAutorizationManagerImpl implements JokoAuthorizationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokoAutorizationManagerImpl.class);
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
                .authenticationDetailsSource(authenticationDetailsSource())
                .and()
                .authorizeRequests()
                .antMatchers(io.github.jokoframework.security.ApiPaths.LOGIN).permitAll()
                .antMatchers(ApiPaths.SIGNUP).permitAll()
                .antMatchers(ApiPaths.API_SESSIONS).hasAnyAuthority(RolEnum.ADMIN.name())
                // Users
                .antMatchers(ApiPaths.CREATE_USER,
                        ApiPaths.DELETE_USER,
                        ApiPaths.GET_USER,
                        ApiPaths.LIST_USERS,
                        ApiPaths.UPDATE_USER,
                        ApiPaths.CHANGE_USER_STATUS).hasAnyAuthority(RolEnum.ADMIN.name());

        // Only in dev profile,
        // Allows X-Frame-Options headers sent by H2 console.
        // http://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
    }

    @Override
    public Collection<? extends GrantedAuthority> authorize(JokoJWTClaims jokoJWTClaims, Collection<? extends GrantedAuthority> collection) {
        return collection;
    }

    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource() {
        return new AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>() {
            @Override
            public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
                CustomAuthenticationDetails details = new CustomAuthenticationDetails(request);
                details.addCustom(CustomAuthenticationDetails.USER_DATE, System.currentTimeMillis())
                        .addCustom(CustomAuthenticationDetails.IP_ADDRESS, getIp()
                                .orElse((String) details.getCustom().get(CustomAuthenticationDetails.HOST)));
                return details;
            }

            private Optional<String> getIp() {
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    return Optional.of(ip.getHostAddress());
                } catch (UnknownHostException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                return Optional.empty();
            }
        };
    }
}
