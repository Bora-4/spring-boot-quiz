package com.quiz.repository.impl;

import com.quiz.entity.QuizResultEntity;
import com.quiz.repository.QuizResultRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizResultImpl implements QuizResultRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(QuizResultEntity quizResult) {
        em.persist(quizResult);
    }

    @Override
    public QuizResultEntity findById(Long id) {
        return em.find(QuizResultEntity.class, id);
    }

    @Override
    public void update(QuizResultEntity quizResult) {
        em.merge(quizResult);
    }

    @Override
    public List<QuizResultEntity> findAll() {

        return em.createQuery("SELECT quiz_result FROM QuizResultEntity quiz_result", QuizResultEntity.class)
                .getResultList();
    }

    @Override
    public void delete(Long id) {
        em.remove(id);
    }
}
