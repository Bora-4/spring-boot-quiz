package com.quiz.service.impl;

import com.quiz.dto.QuizDTO;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    public QuizServiceImpl(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }
    @Override
    public void save(QuizDTO quizDTO) {

    }

    @Override
    public QuizDTO findById(Long id) {
        return null;
    }

    @Override
    public void update(QuizDTO quizDTO) {

    }

    @Override
    public List<QuizDTO> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
