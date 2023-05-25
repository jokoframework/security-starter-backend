package io.github.jokoframework.securitystarterbackend.services;

import io.github.jokoframework.securitystarterbackend.constants.StatusEnum;
import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.entities.UserEntity;
import io.github.jokoframework.securitystarterbackend.exception.UserException;

import java.util.List;

public interface UserService {
    public UserResponseDTO createUser(UserRequestDTO user) throws UserException;
    public UserResponseDTO updateUser(Long userId, UserRequestDTO user) throws UserException;
    public void deleteUser(Long userId) throws UserException;
    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO getUserById(Long userId) throws UserException;
    public void changeUserStatus(Long userId, StatusEnum newStatus) throws UserException;

    public UserEntity validateUser(String username, String password) throws UserException;
}
