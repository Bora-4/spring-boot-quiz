package com.quiz.service;

import com.quiz.dto.CreateUserRequest;
import com.quiz.dto.UserDTO;
import com.quiz.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void save(UserDTO userDTO, Long roleId);

    UserDTO findById(Long id);
    void update(UserDTO userDTO);
    List<UserDTO> findAll();
    void delete(Long id);
}
