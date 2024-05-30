package com.quiz.repository;

import com.quiz.entity.OptionEntity;
import com.quiz.entity.QuizResultEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository {
    void save(QuizResultEntity quizResult);
    QuizResultEntity findById(Long id);
    void update(QuizResultEntity quizResult);
    List<QuizResultEntity> findAll();
    void delete(Long id);
}
