package com.quiz.service.impl;

import com.quiz.dto.QuizResultDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.QuizResultEntity;
import com.quiz.entity.UserEntity;
import com.quiz.mapper.QuizResultMapper;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.QuizResultRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.QuizResultService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizResultsServiceImpl implements QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizResultsServiceImpl(QuizResultRepository quizResultRepository, QuizRepository quizRepository, UserRepository userRepository) {
        this.quizResultRepository = quizResultRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(QuizResultDTO quizResultDTO) {
        QuizEntity quiz = quizRepository.findById(quizResultDTO.getQuiz().getId());
        UserEntity user = userRepository.findByUsername(quizResultDTO.getUser().getUsername());
        if( quiz != null && user != null){
            QuizResultEntity quizResult = QuizResultMapper.toEntity(quizResultDTO, user, quiz);
            this.quizResultRepository.save(quizResult);
        }
        else{
            throw new RuntimeException("User or quiz does not exist");
        }
    }

    @Override
    public QuizResultDTO findById(Long id) {
        QuizResultEntity quizResult = this.quizResultRepository.findById(id);
        if( quizResult != null){
            return QuizResultMapper.toDTO(quizResult);
        }
        throw new RuntimeException("Quiz result with id "+id+" does not exist.");
    }

    @Override
    public void update(QuizResultDTO quizResultDTO) {
        QuizResultEntity quizResult = this.quizResultRepository.findById(quizResultDTO.getId());
        if( quizResult != null){
            this.quizResultRepository.update(quizResult);
        }
        throw new RuntimeException("Quiz result with id "+quizResultDTO.getId()+" does not exist.");
    }

    @Override
    public List<QuizResultDTO> findAll() {
        return this.quizResultRepository.findAll().stream().map(QuizResultMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        this.quizResultRepository.delete(id);
    }
}
