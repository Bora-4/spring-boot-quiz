package com.quiz.service.impl;

import com.quiz.entity.UserEntity;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(UserEntity user){
        userRepository.save(user);
    }
}
