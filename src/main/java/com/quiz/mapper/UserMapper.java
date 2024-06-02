package com.quiz.mapper;

import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper(){
        // private constructor
    }

    public static UserEntity toEntity(UserDTO userDTO){
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public static UserDTO toDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        //shiko nje menyre per te vendosur role
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

}
