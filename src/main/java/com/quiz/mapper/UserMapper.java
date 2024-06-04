package com.quiz.mapper;

import com.quiz.dto.RoleDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;

import java.util.Set;
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
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setEnabled(userDTO.getEnabled());
        Set<RoleEntity> roleEntities = userDTO.getRoles().stream()
                .map(UserMapper::toRoleEntity)
                .collect(Collectors.toSet());
        user.setRoles(roleEntities);
        return user;
    }

    public static UserDTO toDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        Set<RoleDTO> roleDTOs = user.getRoles().stream()
                .map(UserMapper::toRoleDTO)
                .collect(Collectors.toSet());
        userDTO.setRoles(roleDTOs);

        userDTO.setEnabled(user.getEnabled());

        return userDTO;
    }

    public static RoleDTO toRoleDTO(RoleEntity role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public static RoleEntity toRoleEntity(RoleDTO roleDTO){
        RoleEntity role = new RoleEntity();
        role.setId(roleDTO.getId());
        role.setName(role.getName());
        return role;
    }

}
