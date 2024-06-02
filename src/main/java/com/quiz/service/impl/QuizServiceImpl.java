package com.quiz.service.impl;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.UserEntity;
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
        UserEntity user = userRepository.findByUsername(quizDTO.getUser().getUsername());
        if(user != null){
            QuizEntity quiz = QuizMapper.toEntity(quizDTO, user);
            quizRepository.save(quiz);
        }
        else {
            throw new RuntimeException("User with username \""+quizDTO.getUser().getUsername()+"\" does not exist");
        }
    }

    @Override
    public QuizDTO findById(Long id) {
        QuizEntity quiz = quizRepository.findById(id);
        if(quiz != null){
            return QuizMapper.toDTO(quiz);
        }
        throw new RuntimeException("Quiz with id "+id+" does not exist");
    }

    @Override
    public void update(QuizDTO quizDTO) {
        QuizEntity quiz = quizRepository.findById(quizDTO.getId());
        if(quiz != null){
            quizRepository.update(quiz);
        }
        throw new RuntimeException("Quiz with id "+ quizDTO.getId()+" does not exist");
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
