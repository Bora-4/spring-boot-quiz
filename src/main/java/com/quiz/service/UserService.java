package com.quiz.service;

import com.quiz.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void save(UserDTO userDTO, Long roleId);
    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    void update(UserDTO userDTO);
    List<UserDTO> findAll();
    void delete(Long id);
}
