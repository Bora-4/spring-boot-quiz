package com.quiz.service.impl;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.UserEntity;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.mapper.QuizMapper;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    public QuizServiceImpl(QuizRepository quizRepository, UserRepository userRepository){
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(QuizDTO quizDTO) {
        UserEntity user = userRepository.findById(quizDTO.getUser().getId());
        if(user != null){
            QuizEntity quiz = QuizMapper.toEntity(quizDTO, user);
            quizRepository.save(quiz);
        }
        else {
            throw new EntityNotFoundException("User with id "+quizDTO.getUser().getId()+ " was not found");
        }
    }

    @Override
    public QuizDTO findById(Long id) {
        QuizEntity quiz = quizRepository.findById(id);
        if(quiz != null){
            return QuizMapper.toDTO(quiz);
        }
        throw new EntityNotFoundException("Quiz with id "+id+" was not found");
    }

    @Override
    public void update(QuizDTO quizDTO) {
        QuizEntity quiz = quizRepository.findById(quizDTO.getId());
        if(quiz != null){
            quizRepository.update(quiz);
        }
        throw new EntityNotFoundException("Quiz with id "+quizDTO.getId()+" was not found");
    }

    @Override
    public List<QuizDTO> findAll() {
        return quizRepository.findAll().stream()
                .map(QuizMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        this.quizRepository.delete(id);
    }
}
