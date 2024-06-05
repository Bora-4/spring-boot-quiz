package com.quiz.service.impl;


import com.quiz.dto.CreateUserRequest;
import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import com.quiz.entity.UserEntity;
import com.quiz.entity.UserRole;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.mapper.UserMapper;
import com.quiz.repository.UserRepository;
import com.quiz.service.RoleRepository;
import com.quiz.service.UserService;
import com.quiz.util.PasswordValidator;
import com.quiz.util.UsernameValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void save(UserDTO userDTO, Long roleId) {
        //validate username
        UsernameValidator.validate(userDTO.getUsername());
        //validate password
        PasswordValidator.validate(userDTO.getPassword());

        //encode the password before saving (nuk e kam bere akoma pjesen e security)
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Fetch the role by ID
        RoleEntity role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role with id " + roleId + " not found"));

        // Map the user DTO to entity and add the role
        UserEntity userEntity = UserMapper.toEntity(userDTO, role);

        // Save the user entity
        userRepository.save(userEntity);

    }


    @Override
    public UserDTO findById(Long id) {
        UserEntity user = this.userRepository.findById(id);
        if(user != null){
            return UserMapper.toDTO(user);
        }
        throw new EntityNotFoundException("User with id "+id+ " was not found");
    }

    @Override
    public void update(UserDTO userDTO) {
        UserEntity user = this.userRepository.findById(userDTO.getId());
        if(user != null){
            this.userRepository.update(user);
        }
        throw new EntityNotFoundException("User with id "+userDTO.getId()+ " was not found");
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
