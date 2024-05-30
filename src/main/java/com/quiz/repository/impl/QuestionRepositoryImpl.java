package com.quiz.repository.impl;

import com.quiz.entity.QuestionEntity;
import com.quiz.entity.QuizEntity;
import com.quiz.repository.QuestionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(QuestionEntity question) {
        em.persist(question);
    }

    @Override
    public QuestionEntity findById(Long id) {
        return em.find(QuestionEntity.class, id);
    }

    @Override
    public void update(QuestionEntity question) {
        em.merge(question);
    }

    @Override
    public List<QuestionEntity> findAll() {
        return em.createQuery("SELECT question FROM QuestionEntity question", QuestionEntity.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.remove(id);
    }
}
