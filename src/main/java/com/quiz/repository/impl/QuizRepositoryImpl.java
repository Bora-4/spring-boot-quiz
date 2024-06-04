package com.quiz.repository.impl;

import com.quiz.entity.QuizEntity;
import com.quiz.repository.QuizRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRepositoryImpl implements QuizRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(QuizEntity quiz) {
        em.persist(quiz);
    }

    @Override
    public QuizEntity findById(Long id) {
        return em.find(QuizEntity.class, id);
    }

    @Override
    public void update(QuizEntity quiz) {
        em.merge(quiz);
    }

    @Override
    public List<QuizEntity> findAll() {
        return em.createQuery("SELECT q FROM QuizEntity q", QuizEntity.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id));
    }
}
