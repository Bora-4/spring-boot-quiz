package com.quiz.service.impl;

import com.quiz.entity.RoleEntity;
import com.quiz.service.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public RoleEntity findById(Long id) {
        return em.find(RoleEntity.class,  id);
    }
}
