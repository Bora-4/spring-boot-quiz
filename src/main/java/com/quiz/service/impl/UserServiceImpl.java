package com.quiz.service.impl;


import com.quiz.dto.CreateUserRequest;
import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;
import com.quiz.entity.UserRole;
import com.quiz.mapper.UserMapper;
import com.quiz.repository.UserRepository;
import com.quiz.service.RoleRepository;
import com.quiz.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(CreateUserRequest createUserRequest) {
        // Check if the role exists
        RoleEntity role = roleRepository.findById(createUserRequest.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        // Create a new user entity
        UserEntity user = new UserEntity();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setUsername(createUserRequest.getUsername());
        user.setEnabled(createUserRequest.isEnabled());

        // Add the role to the user's roles
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        user.getUserRoles().add(userRole);

        // Set timestamps
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        // Save the user
        userRepository.save(user);
    }



    @Override
    public UserDTO findById(Long id) {
        UserEntity user = this.userRepository.findById(id);
        if(user != null){
            return UserMapper.toDTO(user);
        }
        throw new RuntimeException("User with id " +id+" does not exist. ");
    }

    @Override
    public void update(UserDTO userDTO) {
        UserEntity user = this.userRepository.findById(userDTO.getId());
        if(user != null){
            this.userRepository.update(user);
        }
        throw new RuntimeException("User with id " +userDTO.getId()+" does not exist. ");

    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
