package com.quiz.service;

import com.quiz.dto.QuizResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizResultService {
    void save(QuizResultDTO quizResultDTO);
    QuizResultDTO findById(Long id);
    void update(QuizResultDTO quizResultDTO);
    List<QuizResultDTO> findAll();
    void delete(Long id);
}
