package com.quiz.service;

import com.quiz.dto.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    void save(QuestionDTO questionDTO);
    QuestionDTO findById(Long id);
    void update(QuestionDTO questionDTO);
    List<QuestionDTO> findAll();
    void delete(Long id);
}
