package io.github.jokoframework.securitystarterbackend.services.impl;

import io.github.jokoframework.security.util.SecurityUtils;
import io.github.jokoframework.securitystarterbackend.constants.StatusEnum;
import io.github.jokoframework.securitystarterbackend.services.UserService;
import io.github.jokoframework.securitystarterbackend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.jokoframework.securitystarterbackend.constants.ApiConstants;
import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.entities.UserEntity;
import io.github.jokoframework.securitystarterbackend.exception.UserException;
import io.github.jokoframework.securitystarterbackend.mapper.UserMapper;
import io.github.jokoframework.securitystarterbackend.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws UserException {
        UserEntity userEntity = UserMapper.toEntity(userRequestDTO);
        if(userRepository.existsByUsername(userEntity.getUsername())){
            throw UserException.userAlreadyExists(userEntity.getUsername());
        }
        UserUtils.validateEmail(userEntity.getEmail());
        UserUtils.validatePassword(userEntity.getPassword());
        userEntity.setPassword(SecurityUtils.hashPassword(userEntity.getPassword())); //se guarda la contraseña cifrada
        userEntity.setStatus(StatusEnum.ACTIVO);
        userEntity.setCreationDate(UserUtils.getCurrentDateTime());
        return UserMapper.toResponseDTO(userRepository.save(userEntity));
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) throws UserException{
        if(userId != null && userRepository.existsById(userId)) {
            UserEntity userFromDB =  userRepository.findById(userId).get();
            updateAttributes(userFromDB, userRequestDTO);
            return UserMapper.toResponseDTO(userRepository.save(userFromDB));
        }else {
            throw UserException.notFound(userId);
        }
    }

    @Override
    public void deleteUser(Long userId) throws UserException{
        if(userId != null && userRepository.existsById(userId) ) {
            userRepository.deleteById(userId);
        }else {
            throw UserException.notFound(userId);
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers(){
        return UserMapper.toResponseDTOList(userRepository.findAll());
    }

    @Override
    public UserResponseDTO getUserById(Long userId) throws UserException{
        if(userId != null && userRepository.existsById(userId)){
            return UserMapper.toResponseDTO(userRepository.findById(userId).get());
        }else{
            throw UserException.notFound(userId);
        }
    }

    @Override
    public void changeUserStatus(Long userId, StatusEnum newStatus) throws UserException {
        if(userId != null && newStatus != null && userRepository.existsById(userId)){
            UserEntity existingUser = userRepository.findById(userId).get();
            existingUser.setStatus(newStatus);
            userRepository.save(existingUser);
        }else{
            throw UserException.notFound(userId);
        }
    }

    @Override
    public UserEntity validateUser(String username, String password) throws UserException{
        if(!UserUtils.isEmptyString(username) && userRepository.existsByUsername(username)){
            UserEntity userEntity = userRepository.findByUsername(username).get();
            if(userEntity != null && SecurityUtils.matchPassword(password, userEntity.getPassword())){
                return userEntity;
            }else{
                throw UserException.invalidUserCredentials();
            }
        }else{
            throw UserException.invalidUserCredentials();
        }
    }

    private void updateAttributes(UserEntity existingUserEntity, UserRequestDTO userRequestDTO){
        if(!UserUtils.isEmptyString(userRequestDTO.getUsername())
                && !userRequestDTO.getUsername().equals(existingUserEntity.getUsername())
                && !userRepository.existsByUsername(userRequestDTO.getUsername())){

            existingUserEntity.setUsername(userRequestDTO.getUsername());
        }
        if(!UserUtils.isEmptyString(userRequestDTO.getEmail())
                && !userRequestDTO.getEmail().equals(existingUserEntity.getEmail())
                && UserUtils.isValidEmail(userRequestDTO.getEmail())){

            existingUserEntity.setEmail(userRequestDTO.getEmail());
        }
        if(!UserUtils.isEmptyString(userRequestDTO.getPassword())
                && !SecurityUtils.matchPassword(userRequestDTO.getPassword(), existingUserEntity.getPassword())
                && userRequestDTO.getPassword().length() >= ApiConstants.MIN_LENGTH_PASS){

            //La contraseña se guarda cifrada
            existingUserEntity.setPassword(SecurityUtils.hashPassword(userRequestDTO.getPassword()));
        }
    }
}
