package com.quiz.repository;

import com.quiz.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository {
    void save(QuestionEntity question);
    QuestionEntity findById(Long id);
    void update(QuestionEntity hotel);
    List<QuestionEntity> findAll();
    void delete(Long id);
}
