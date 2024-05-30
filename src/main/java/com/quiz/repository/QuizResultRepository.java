package com.quiz.repository;

import com.quiz.entity.OptionEntity;
import com.quiz.entity.QuizResultEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository {
    void save(QuizResultEntity quizResult);
    QuizResultEntity getOption(Long id);
    void update(QuizResultEntity quizResult);
    List<QuizResultEntity> getAllOptions();
    void delete(Long id);
}
