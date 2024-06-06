package com.quiz.service;

import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.exceptions.requirements.PasswordRequirementsException;
import com.quiz.exceptions.requirements.UsernameRequirementsException;
import com.quiz.mapper.UserMapper;
import com.quiz.repository.UserRepository;
import com.quiz.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;


    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser_success() {
        // Create a user DTO with valid data
        UserDTO userDTO = UserDTO.builder()
                .username("testUser")
                .password("ValidPass123#")
                .email("test@example.com")
                .build();

        // Mock the RoleRepository to return a valid RoleEntity
        when(roleRepository.findById(any())).thenReturn(Optional.of(new RoleEntity()));

        // Mock the UserRepository's save method
        doNothing().when(userRepository).save(any());

        // Call the save method
        assertDoesNotThrow(() -> userService.save(userDTO, 1L));

        // Verify that the UserRepository's save method was called once
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void saveUser_invalidUsername_throwsException() {
        // Create a user DTO with an invalid username
        UserDTO userDTO = UserDTO.builder()
                .username("us")
                .password("ValidPass123#")
                .email("test@example.com")
                .build();

        // Call the save method and expect an exception to be thrown
        assertThrows(UsernameRequirementsException.class, () -> userService.save(userDTO, 1L));

        // Verify that the UserRepository's save method was not called
        verify(userRepository, never()).save(any());
    }

    @Test
    void saveUser_invalidPassword_throwsException() {
        // Create a user DTO with an invalid password
        UserDTO userDTO = UserDTO.builder()
                .username("testUser")
                .password("invalid")
                .email("test@example.com")
                .build();

        // Call the save method and expect an exception to be thrown
        assertThrows(PasswordRequirementsException.class, () -> userService.save(userDTO, 1L));

        // Verify that the UserRepository's save method was not called
        verify(userRepository, never()).save(any());
    }

    @Test
    void update_nonExistingUser_throwEntityNotFoundException() {
        // Mock data
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        // Mock repository method
        when(userRepository.findById(userDTO.getId())).thenReturn(null);

        // Call update method and verify that EntityNotFoundException is thrown
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.update(userDTO));
        assertEquals("User with id 1 was not found", exception.getMessage());

        // Verify that userRepository.update was not called
        verify(userRepository, never()).update(any());
    }

    @Test
    void findAll_usersExist_returnListOfUsers() {
        // Mock data
        UserEntity userEntity = new UserEntity();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));

        // Call findAll method
        List<UserDTO> userDTOList = userService.findAll();

        // Verify that the returned list is not null and contains one user
        assertNotNull(userDTOList);
        assertEquals(1, userDTOList.size());

        // Verify that UserMapper.toDTO was called once
        verify(userMapper, times(1)).toDTO(userEntity);
    }


    @Test
    void delete_existingUser_userDeleted() {
        // Mock data
        Long userId = 1L;

        // Call delete method
        assertDoesNotThrow(() -> userService.delete(userId));

        // Verify that userRepository.delete was called once with the correct parameter
        verify(userRepository, times(1)).delete(userId);
    }
    @Test
    void delete_nonExistingUser_throwEntityNotFoundException() {
        // Mock data
        Long userId = 1L;

        // Mock repository method
        when(userRepository.findById(userId)).thenReturn(null);

        // Call delete method and verify that EntityNotFoundException is thrown
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.delete(userId));
        assertEquals("User with id 1 was not found", exception.getMessage());

        // Verify that userRepository.delete was not called
        verify(userRepository, never()).delete(userId);
    }




}
