package com.quiz.service.impl;

import com.quiz.dto.QuizResultDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.QuizResultEntity;
import com.quiz.entity.UserEntity;
import com.quiz.exceptions.notFound.EntityNotFoundException;
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
        if (quizResultDTO.getQuiz() == null || quizResultDTO.getQuiz().getId() == null) {
            throw new IllegalArgumentException("Quiz information is required");
        }
        if (quizResultDTO.getUser() == null || quizResultDTO.getUser().getId() == null) {
            throw new IllegalArgumentException("User information is required");
        }

        QuizEntity quiz = quizRepository.findById(quizResultDTO.getQuiz().getId());
        UserEntity user = userRepository.findById(quizResultDTO.getUser().getId());

        if (quiz == null) {
            throw new EntityNotFoundException("Quiz with id " + quizResultDTO.getQuiz().getId() + " was not found");
        }
        if (user == null) {
            throw new EntityNotFoundException("User with id " + quizResultDTO.getUser().getId() + " was not found");
        }

        QuizResultEntity quizResult = QuizResultMapper.toEntity(quizResultDTO, user, quiz);
        quizResultRepository.save(quizResult);
    }

    @Override
    public QuizResultDTO findById(Long id) {
        QuizResultEntity quizResult = quizResultRepository.findById(id);
        if (quizResult != null) {
            return QuizResultMapper.toDTO(quizResult);
        }
        throw new EntityNotFoundException("Quiz result with id " + id + " was not found");
    }

    @Override
    public void update(QuizResultDTO quizResultDTO) {
        if (quizResultDTO.getId() == null) {
            throw new IllegalArgumentException("Quiz result ID is required for update");
        }

        QuizResultEntity quizResult = quizResultRepository.findById(quizResultDTO.getId());
        if (quizResult != null) {
            quizResultRepository.update(quizResult);
        } else {
            throw new EntityNotFoundException("Quiz result with id " + quizResultDTO.getId() + " was not found");
        }
    }

    @Override
    public List<QuizResultDTO> findAll() {
        return quizResultRepository.findAll().stream()
                .map(QuizResultMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        QuizResultEntity quizResult = quizResultRepository.findById(id);
        if (quizResult != null) {
            quizResultRepository.delete(quizResult.getId());
        } else {
            throw new EntityNotFoundException("Quiz result with id " + id + " was not found");
        }
    }
}
