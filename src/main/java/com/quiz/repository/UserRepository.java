package com.quiz.repository;

import com.quiz.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    void save(UserEntity userEntity);
    UserEntity findByUsername(String username);
    void update(UserEntity userEntity);
    List<UserEntity> findAll();
    void delete(Long id);

}
