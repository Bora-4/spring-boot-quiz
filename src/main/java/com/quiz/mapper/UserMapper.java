package com.quiz.mapper;

import com.quiz.dto.RoleDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;
import com.quiz.entity.UserRole;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {
    private UserMapper() {
        // private constructor
    }

    public static UserEntity toEntity(UserDTO userDTO, RoleEntity role) {
        if (userDTO == null || role == null) {
            throw new NullPointerException("UserDTO and RoleEntity cannot be null");
        }

        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setEnabled(userDTO.getEnabled());

        // Create a new HashSet to hold the user's roles
        Set<UserRole> userRoles = new HashSet<>();

        // Create a new UserRole entity and set the user and role
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        // Add the UserRole entity to the set of user roles
        userRoles.add(userRole);

        // Set the user's roles
        user.setUserRoles(userRoles);

        return user;
    }

    public static UserDTO toDTO(UserEntity user) {
        if (user == null) {
            throw new NullPointerException("UserEntity cannot be null");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setEnabled(user.getEnabled());

        // Map user roles
        Set<RoleDTO> roleDTOs = new HashSet<>();
        if (user.getUserRoles() != null) {
            user.getUserRoles().forEach(userRole -> roleDTOs.add(toRoleDTO(userRole.getRole())));
        }
        userDTO.setRoles(roleDTOs);
        return userDTO;
    }

    public static RoleDTO toRoleDTO(RoleEntity role) {
        if (role == null) {
            throw new NullPointerException("RoleEntity cannot be null");
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public static RoleEntity toRoleEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            throw new NullPointerException("RoleDTO cannot be null");
        }

        RoleEntity role = new RoleEntity();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
