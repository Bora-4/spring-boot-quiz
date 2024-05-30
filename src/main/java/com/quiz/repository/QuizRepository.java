package com.quiz.repository;

import com.quiz.entity.QuizEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository {
    void save(QuizEntity quiz);
    QuizEntity getOption(Long id);
    void update(QuizEntity quiz);
    List<QuizEntity> getAllOptions();
    void delete(Long id);
}
