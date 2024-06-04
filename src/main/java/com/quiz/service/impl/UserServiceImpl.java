package com.quiz.service.impl;


import com.quiz.dto.UserDTO;
import com.quiz.entity.UserEntity;
import com.quiz.mapper.UserMapper;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDTO userDTO) {
        UserEntity user = UserMapper.toEntity(userDTO);
        this.userRepository.save(user);

    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity user = this.userRepository.findById(id);
        if(user != null){
            return UserMapper.toDTO(user);
        }
        throw new RuntimeException("User with username \"" +id+"\" does not exist. ");
    }

    @Override
    public void update(UserDTO userDTO) {
        UserEntity user = this.userRepository.findById(userDTO.getId());
        if(user != null){
            this.userRepository.update(user);
        }
        throw new RuntimeException("User with username \"" +userDTO.getId()+"\" does not exist. ");

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
