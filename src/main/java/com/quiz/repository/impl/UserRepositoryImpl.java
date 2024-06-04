package com.quiz.repository.impl;

import com.quiz.entity.UserEntity;
import com.quiz.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public void save(UserEntity userEntity) {
        em.persist(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        return em.find(UserEntity.class, id);
    }

    @Override
    public void update(UserEntity userEntity) {
        em.merge(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return em.createQuery("SELECT u FROM UserEntity u", UserEntity.class)
                .getResultList();
    }

    @Override
    public void delete(Long id) {
        em.remove(id);
    }
}
