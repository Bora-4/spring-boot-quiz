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
    public void save(UserDTO userDTO, Long roleId) {
        // Check if user already exists
        if (userRepository.findById(userDTO.getId()) == null) {
            // Fetch the role by ID
            RoleEntity role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role with id " + roleId + " not found"));

            // Map the user DTO to entity and add the role
            UserEntity userEntity = UserMapper.toEntity(userDTO, role);

            // Save the user entity
            userRepository.save(userEntity);
        } else {
            throw new RuntimeException("User with username " + userDTO.getUsername() + " already exists");
        }
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
