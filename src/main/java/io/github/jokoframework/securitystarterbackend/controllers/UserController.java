package io.github.jokoframework.securitystarterbackend.controllers;


import io.github.jokoframework.common.dto.JokoBaseResponse;
import io.github.jokoframework.securitystarterbackend.constants.RolEnum;
import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;
import io.github.jokoframework.securitystarterbackend.constants.StatusEnum;
import io.github.jokoframework.securitystarterbackend.exception.SecurityBackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
import java.util.List;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //Crear un nuevo usuario
    @RequestMapping(value = ApiPaths.CREATE_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createUser(@RequestHeader("secret") String secret,
                                                      @RequestBody UserRequestDTO userRequest)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("Request = {1} -> {2}",
                    ApiPaths.CREATE_USER, userRequest.toString()));
            userService.checkSession(secret, RolEnum.ADMIN);
            UserResponseDTO userResponseDTO = userService.createUser(userRequest);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(error.toString());
        }
    }

    //Actualizar datos de un usuario existente
    @RequestMapping(value = ApiPaths.UPDATE_USER, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> updateUser(@RequestHeader("secret") String secret,
                                                      @RequestParam(name = "user_id", required = true) Long userId,
                                                      @RequestBody UserRequestDTO userRequestDTO)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("Request = {1} -> {2} | {3}", ApiPaths.UPDATE_USER,
                    userId, userRequestDTO.toString()));
            userService.checkSession(secret, RolEnum.ADMIN);
            UserResponseDTO userResponseDTO = userService.updateUser(userId, userRequestDTO);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(error.toString());
        }
    }

    //Eliminar un usuario por su ID

    @RequestMapping(value = ApiPaths.DELETE_USER, method = RequestMethod.DELETE)
    public ResponseEntity<JokoBaseResponse> deleteUser(@RequestHeader("secret") String secret,
                                                       @RequestParam(name = "user_id", required = true) Long userId)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("Request = {1} -> {2}" , ApiPaths.DELETE_USER, userId));
            userService.checkSession(secret, RolEnum.ADMIN);
            userService.deleteUser(userId);
            return ResponseEntity.ok(new JokoBaseResponse(true));
        } catch (UserException error) {
            throw error;
        } catch (Exception error) {
            throw new SecurityBackendException(error.toString());
        }
    }

    //Obtener todos los usuarios

    @RequestMapping(value = ApiPaths.LIST_USERS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(@RequestHeader("secret") String secret) throws SecurityBackendException {
        try {
            userService.checkSession(secret, RolEnum.ADMIN);
            List<UserResponseDTO> userResponseDTOList = userService.getAllUsers();
            return ResponseEntity.ok(userResponseDTOList);
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(error.toString());
        }
    }

    //Obtener un usuario por su ID
    @RequestMapping(value = ApiPaths.GET_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> getUser(@RequestHeader("secret") String secret,
                                                   @RequestParam(name = "user_id", required = true) Long userId)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("Request = {1} -> {2}", ApiPaths.GET_USER, userId));
            userService.checkSession(secret, RolEnum.ADMIN);
            UserResponseDTO userResponseDTO = userService.getUserById(userId);
            userResponseDTO.setSuccess(true);
            return ResponseEntity.ok(userResponseDTO);
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(error.toString());
        }
    }

    //Cambiar estado de un usuario a partir de su ID
    @RequestMapping(value = ApiPaths.CHANGE_USER_STATUS, method = RequestMethod.PUT)
    public ResponseEntity<JokoBaseResponse> changeUserStatus(@RequestHeader("secret") String secret,
                                                             @RequestParam(name = "user_id", required = true) Long userId,
                                                             @RequestParam(name = "status", required = true) StatusEnum status)
            throws UserException, SecurityBackendException {
        try {
            LOGGER.info(MessageFormat.format("Request = {1} -> {2} | {3}",
                    ApiPaths.CHANGE_USER_STATUS, userId, status));
            userService.checkSession(secret, RolEnum.ADMIN);
            userService.changeUserStatus(userId, status);
            return ResponseEntity.ok(new JokoBaseResponse(true));
        } catch (UserException error) {
            LOGGER.error(error.getMessage(), error);
            throw error;
        } catch (Exception error) {
            LOGGER.error(error.getMessage(), error);
            throw new SecurityBackendException(error.toString());
        }
    }
}
