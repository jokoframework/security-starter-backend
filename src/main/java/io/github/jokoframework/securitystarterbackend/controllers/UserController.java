package io.github.jokoframework.securitystarterbackend.controllers;

import io.github.jokoframework.common.dto.JokoBaseResponse;
import io.github.jokoframework.securitystarterbackend.constants.StatusEnum;
import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //Crear un nuevo usuario
    @RequestMapping(value = ApiPaths.CREATE_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("\nEndpoint: {0} \nParámetros: {1}" ,
                    ApiPaths.USER + ApiPaths.CREATE_USER, userRequest.toString()));
            UserResponseDTO userResponseDTO = userService.createUser(userRequest);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    error.getMessage(), error.getCause()));
        }
    }

    //Actualizar datos de un usuario existente
    @RequestMapping(value = ApiPaths.UPDATE_USER, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam(name = "userId", required = true) Long userId, @RequestBody UserRequestDTO userRequestDTO)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("\nEndpoint: {0} \nParámetros: {1} , {2}" ,
                    ApiPaths.USER + ApiPaths.UPDATE_USER, userId, userRequestDTO.toString()));
            UserResponseDTO userResponseDTO = userService.updateUser(userId, userRequestDTO);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    error.getMessage(), error.getCause()));
        }
    }

    //Eliminar un usuario por su ID
    @RequestMapping(value = ApiPaths.DELETE_USER, method = RequestMethod.DELETE)
    public ResponseEntity<JokoBaseResponse> deleteUser(@RequestParam(name = "userId", required = true) Long userId)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("\nEndpoint: {0} \nParámetros: {1}" ,
                    ApiPaths.USER + ApiPaths.DELETE_USER, userId));
            userService.deleteUser(userId);
            return ResponseEntity.ok(new JokoBaseResponse(true));
        } catch (UserException error) {
            throw error;
        } catch (Exception error) {
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    error.getMessage(), error.getCause()));
        }
    }

    //Obtener todos los usuarios
    @RequestMapping(value = ApiPaths.LIST_USERS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() throws SecurityBackendException {
        try {
            List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
            return ResponseEntity.ok(userResponseDTOList);
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    error.getMessage(), error.getCause()));
        }
    }

    //Obtener un usuario por su ID
    @RequestMapping(value = ApiPaths.GET_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam(name = "userId", required = true) Long userId)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("\nEndpoint: {0} \nParámetros: {1}" ,
                    ApiPaths.USER + ApiPaths.GET_USER, userId));
            UserResponseDTO userResponseDTO = userService.getUserById(userId);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    error.getMessage(), error.getCause()));
        }
    }

    //Cambiar estado de un usuario a partir de su ID
    @RequestMapping(value = ApiPaths.UPDATE_USER + "/status", method = RequestMethod.PUT)
    public ResponseEntity<JokoBaseResponse> changeUserStatus(@RequestParam(name = "userId", required = true) Long userId,
                                                             @RequestParam(name = "status", required = true) StatusEnum status)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("\nEndpoint: {0} \nParámetros: {1}, {2}" ,
                    ApiPaths.USER + ApiPaths.UPDATE_USER + "/status", userId, status));
            userService.changeUserStatus(userId, status);
            return ResponseEntity.ok(new JokoBaseResponse(true));
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(MessageFormat.format("Ha ocurrido un error.Mensaje: {0} Causa: {1}",
                    error.getMessage(), error.getCause()));
        }
    }
}
