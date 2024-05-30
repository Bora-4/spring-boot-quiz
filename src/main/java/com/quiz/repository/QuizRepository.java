package com.quiz.repository;

import com.quiz.entity.QuizEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository {
    void save(QuizEntity quiz);
    QuizEntity findById(Long id);
    void update(QuizEntity quiz);
    List<QuizEntity> findAll();
    void delete(Long id);
}
