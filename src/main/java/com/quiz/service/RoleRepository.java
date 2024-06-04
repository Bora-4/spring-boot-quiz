package com.quiz.service;

import com.quiz.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
    RoleEntity findById(Long id);
}
