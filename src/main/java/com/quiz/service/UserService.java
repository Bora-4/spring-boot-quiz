package com.quiz.service;

import com.quiz.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(UserEntity user);
}
