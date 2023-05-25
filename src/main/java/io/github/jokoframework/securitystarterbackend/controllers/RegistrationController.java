package io.github.jokoframework.securitystarterbackend.controllers;

import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;
import io.github.jokoframework.securitystarterbackend.exception.SecurityBackendException;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(ApiPaths.USER)
public class RegistrationController {
    @Autowired
    private UserService userService;

    //Registra un nuevo usuario en la base de datos
    @RequestMapping(value = ApiPaths.SIGNUP, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO registrationRequest)
            throws UserException, SecurityBackendException{
        try {
            UserResponseDTO userResponseDTO = userService.createUser(registrationRequest);
            return ResponseEntity.ok(userResponseDTO);
        }catch (UserException error) {
            throw error;
        }catch (Exception err) {
            throw  new  SecurityBackendException(String.format("Ha ocurrido un error. \nMensaje: {} \nCausa: {}",
                    err.getMessage(), err.getCause()));
        }
    }
}
