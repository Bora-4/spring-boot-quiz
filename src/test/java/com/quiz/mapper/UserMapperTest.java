package com.quiz.mapper;

import com.quiz.dto.RoleDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;
import com.quiz.entity.UserRole;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void testToEntity_NullUserDTO() {
        RoleEntity role = new RoleEntity();
        assertThrows(NullPointerException.class, () -> UserMapper.toEntity(null, role));
    }

    @Test
    void testToEntity_NullRole() {
        UserDTO userDTO = new UserDTO();
        assertThrows(NullPointerException.class, () -> UserMapper.toEntity(userDTO, null));
    }

    @Test
    void testToEntity_ValidUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("test@example.com");
        userDTO.setUsername("testuser");
        userDTO.setPassword("password");
        userDTO.setCreatedAt(LocalDateTime.now());
        userDTO.setUpdatedAt(LocalDateTime.now());
        userDTO.setEnabled(true);

        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setName("ROLE_USER");

        UserEntity userEntity = UserMapper.toEntity(userDTO, role);

        assertNotNull(userEntity, "UserEntity should not be null for valid UserDTO");
        assertEquals(1L, userEntity.getId());
        assertEquals("test@example.com", userEntity.getEmail());
        assertEquals("testuser", userEntity.getUsername());
        assertEquals("password", userEntity.getPassword());
        assertNotNull(userEntity.getCreatedAt());
        assertNotNull(userEntity.getUpdatedAt());
        assertTrue(userEntity.getEnabled());

        assertNotNull(userEntity.getUserRoles());
        assertEquals(1, userEntity.getUserRoles().size());
        UserRole userRole = userEntity.getUserRoles().iterator().next();
        assertEquals(userEntity, userRole.getUser());
        assertEquals(role, userRole.getRole());
    }

    @Test
    void testToDTO_NullUserEntity() {
        assertThrows(NullPointerException.class, () -> UserMapper.toDTO(null));
    }

    @Test
    void testToDTO_ValidUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@example.com");
        userEntity.setUsername("testuser");
        userEntity.setPassword("password");
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setEnabled(true);

        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setName("ROLE_USER");

        UserRole userRole = new UserRole();
        userRole.setUser(userEntity);
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        userEntity.setUserRoles(userRoles);

        UserDTO userDTO = UserMapper.toDTO(userEntity);

        assertNotNull(userDTO, "UserDTO should not be null for valid UserEntity");
        assertEquals(1L, userDTO.getId());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals("testuser", userDTO.getUsername());
        assertEquals("password", userDTO.getPassword());
        assertNotNull(userDTO.getCreatedAt());
        assertNotNull(userDTO.getUpdatedAt());
        assertTrue(userDTO.getEnabled());

        assertNotNull(userDTO.getRoles());
        assertEquals(1, userDTO.getRoles().size());
        RoleDTO roleDTO = userDTO.getRoles().iterator().next();
        assertEquals(1L, roleDTO.getId());
        assertEquals("ROLE_USER", roleDTO.getName());
    }

    @Test
    void testToRoleDTO_NullRoleEntity() {
        assertThrows(NullPointerException.class, () -> UserMapper.toRoleDTO(null));
    }

    @Test
    void testToRoleDTO_ValidRoleEntity() {
        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setName("ROLE_USER");

        RoleDTO roleDTO = UserMapper.toRoleDTO(role);

        assertNotNull(roleDTO, "RoleDTO should not be null for valid RoleEntity");
        assertEquals(1L, roleDTO.getId());
        assertEquals("ROLE_USER", roleDTO.getName());
    }

    @Test
    void testToRoleEntity_NullRoleDTO() {
        assertThrows(NullPointerException.class, () -> UserMapper.toRoleEntity(null));
    }

    @Test
    void testToRoleEntity_ValidRoleDTO() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setName("ROLE_USER");

        RoleEntity roleEntity = UserMapper.toRoleEntity(roleDTO);

        assertNotNull(roleEntity, "RoleEntity should not be null for valid RoleDTO");
        assertEquals(1L, roleEntity.getId());
        assertEquals("ROLE_USER", roleEntity.getName());
    }
}
