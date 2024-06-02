package com.quiz.service;

import com.quiz.dto.QuizDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    void save(QuizDTO quizDTO);
    QuizDTO findById(Long id);
    void update(QuizDTO quizDTO);
    List<QuizDTO> findAll();
    void delete(Long id);
}
