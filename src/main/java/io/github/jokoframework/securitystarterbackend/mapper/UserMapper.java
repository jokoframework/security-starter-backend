package io.github.jokoframework.securitystarterbackend.mapper;

import io.github.jokoframework.securitystarterbackend.dto.request.UserRequestDTO;
import io.github.jokoframework.securitystarterbackend.dto.response.UserResponseDTO;
import io.github.jokoframework.securitystarterbackend.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserEntity toEntity(UserRequestDTO requestDTO) {
        UserEntity entity = new UserEntity();
        entity.setUsername(requestDTO.getUsername());
        entity.setEmail(requestDTO.getEmail());
        entity.setPassword(requestDTO.getPassword());
        return entity;
    }

    public static UserResponseDTO toResponseDTO(UserEntity entity) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(entity.getUserId());
        responseDTO.setUsername(entity.getUsername());
        responseDTO.setEmail(entity.getEmail());
        responseDTO.setStatus(entity.getStatus());
        responseDTO.setCreationDate(entity.getCreationDate());
        responseDTO.setLastAccess(entity.getLastAccess());
        return responseDTO;
    }

    public static List<UserResponseDTO> toResponseDTOList(List<UserEntity> entities) {
        List<UserResponseDTO> dtos = new ArrayList<>();

        for (UserEntity entity : entities) {
            UserResponseDTO dto = toResponseDTO(entity);
            dtos.add(dto);
        }
        return dtos;
    }
}
