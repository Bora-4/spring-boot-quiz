package com.quiz.mapper;

import com.quiz.dto.RoleDTO;
import com.quiz.dto.UserDTO;
import com.quiz.dto.UserRoleDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;
import com.quiz.entity.UserRole;

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
        Set<UserRole> userRoles = userDTO.getRoles().stream()
                    .map(roleDTO -> toUserRoleEntity(new UserRoleDTO(), user, toRoleEntity(roleDTO)))
                    .collect(Collectors.toSet());
        user.setUserRoles(userRoles);

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
        Set<RoleDTO> roles = user.getUserRoles().stream()
                .map(userRole -> toRoleDTO(userRole.getRole()))
                .collect(Collectors.toSet());
        userDTO.setRoles(roles);
        userDTO.setEnabled(user.getEnabled());

        return userDTO;
    }

    public static UserRole toUserRoleEntity(UserRoleDTO userRoleDTO, UserEntity user, RoleEntity role){
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDTO.getId());
        userRole.setRole(role);
        userRole.setUser(user);
        return userRole;
    }

    public static UserRoleDTO toUserRoleDTO(UserRole userRole){
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId(userRole.getId());
        userRoleDTO.setUser(UserMapper.toDTO(userRole.getUser()));
        userRoleDTO.setRole(UserMapper.toRoleDTO(userRole.getRole()));
        return userRoleDTO;
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
