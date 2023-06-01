package io.github.jokoframework.securitystarterbackend.controllers;

import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;
import io.github.jokoframework.securitystarterbackend.exception.SecurityBackendException;
import org.springframework.http.ResponseEntity;

import java.text.MessageFormat;

@RestController
@RequestMapping(ApiPaths.USER)
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private UserService userService;

    //Registra un nuevo usuario en la base de datos
    @RequestMapping(value = ApiPaths.SIGNUP, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO registrationRequest)
            throws UserException, SecurityBackendException{
        try {
            LOGGER.info(MessageFormat.format("\nEndpoint: {0} \nParámetros: {1}" ,
                    ApiPaths.USER + ApiPaths.SIGNUP, registrationRequest.toString()));
            UserResponseDTO userResponseDTO = userService.createUser(registrationRequest);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        }catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        }catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw  new  SecurityBackendException(String.format("Ha ocurrido un error. \nMensaje: {} \nCausa: {}",
                    error.getMessage(), error.getCause()));
        }
    }
}
