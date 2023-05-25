package io.github.jokoframework.securitystarterbackend.controllers;

import io.github.jokoframework.common.dto.JokoBaseResponse;
import io.github.jokoframework.securitystarterbackend.constants.StatusEnum;
import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;
import io.github.jokoframework.securitystarterbackend.exception.SecurityBackendException;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.USER)
public class UserController {
    @Autowired
    private UserService userService;

    //Crear un nuevo usuario
    @RequestMapping(value = ApiPaths.CREATE_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO registrationRequest)
            throws UserException, SecurityBackendException {
        try {
            UserResponseDTO userResponseDTO = userService.createUser(registrationRequest);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            throw error;
        } catch (Exception err) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    err.getMessage(), err.getCause()));
        }
    }

    //Actualizar datos de un usuario existente
    @RequestMapping(value = ApiPaths.UPDATE_USER, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam(name = "userId", required = true) Long userId, @RequestBody UserRequestDTO userRequestDTO)
            throws UserException, SecurityBackendException {
        try {
            UserResponseDTO userResponseDTO = userService.updateUser(userId, userRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException err) {
            throw err;
        } catch (Exception err) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    err.getMessage(), err.getCause()));
        }
    }

    //Eliminar un usuario por su ID
    @RequestMapping(value = ApiPaths.DELETE_USER, method = RequestMethod.DELETE)
    public ResponseEntity<JokoBaseResponse> deleteUser(@RequestParam(name = "userId", required = true) Long userId)
            throws UserException, SecurityBackendException {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new JokoBaseResponse(true));
        } catch (UserException err) {
            throw err;
        } catch (Exception err) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    err.getMessage(), err.getCause()));
        }
    }

    //Obtener todos los usuarios
    @RequestMapping(value = ApiPaths.LIST_USERS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() throws SecurityBackendException {
        try {
            List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
            return ResponseEntity.ok(userResponseDTOList);
        } catch (Exception err) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    err.getMessage(), err.getCause()));
        }
    }

    //Obtener un usuario por su ID
    @RequestMapping(value = ApiPaths.GET_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam(name = "userId", required = true) Long userId)
            throws UserException, SecurityBackendException {
        try {
            UserResponseDTO userResponseDTO = userService.getUserById(userId);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException err) {
            throw err;
        } catch (Exception err) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    err.getMessage(), err.getCause()));
        }
    }

    //Cambiar estado de un usuario a partir de su ID
    @RequestMapping(value = ApiPaths.UPDATE_USER + "/status", method = RequestMethod.PUT)
    public ResponseEntity<JokoBaseResponse> changeUserStatus(@RequestParam(name = "userId", required = true) Long userId,
                                                             @RequestParam(name = "status", required = true) StatusEnum status)
            throws UserException, SecurityBackendException {
        try {
            userService.changeUserStatus(userId, status);
            return ResponseEntity.ok(new JokoBaseResponse(true));
        } catch (UserException err) {
            throw err;
        } catch (Exception err) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    err.getMessage(), err.getCause()));
        }
    }
}
