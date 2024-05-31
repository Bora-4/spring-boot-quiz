package com.quiz.service.impl;

import com.quiz.dto.QuestionDTO;
import com.quiz.entity.QuestionEntity;
import com.quiz.entity.QuizEntity;
import com.quiz.mapper.QuestionMapper;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    public QuestionServiceImpl(QuestionRepository questionRepository, QuizRepository quizRepository){
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public void save(QuestionDTO questionDTO) {
        QuizEntity quiz = quizRepository.findById(questionDTO.getQuiz().getId());
        if( quiz != null){
            QuestionEntity question = QuestionMapper.toEntity(questionDTO, quiz);
            questionRepository.save(question);
        }
        else{
            throw new RuntimeException("Quiz with id "+ questionDTO.getQuiz().getId()+" does not exist.");
        }
    }

    @Override
    public QuestionDTO findById(Long id) {
        QuestionEntity question = questionRepository.findById(id);
        if( question != null){
            return QuestionMapper.toDTO(question);
        } else {
            throw new RuntimeException("Question with id "+ id + " does not exist.");
        }
    }

    @Override
    public void update(QuestionDTO questionDTO) {
        QuestionEntity question = questionRepository.findById(questionDTO.getId());
        if( question != null){
           questionRepository.update(question);
        } else {
            throw new RuntimeException("Question with id " + questionDTO.getId() +" does not exist.");
        }
    }

     @Override
    public List<QuestionDTO> findAll() {
        return questionRepository.findAll().stream()
                .map(QuestionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        this.questionRepository.delete(id);
    }
}
