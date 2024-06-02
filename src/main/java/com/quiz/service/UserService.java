package com.quiz.service;

import com.quiz.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(UserDTO user);
    UserDTO findByUsername(String username);
}
