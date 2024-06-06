package com.quiz.service;

import com.quiz.dto.QuestionDTO;
import com.quiz.entity.QuestionEntity;
import com.quiz.entity.QuizEntity;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.mapper.QuestionMapper;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.QuizRepository;
import com.quiz.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private QuestionDTO questionDTO;
    private QuestionEntity questionEntity;
    private QuizEntity quizEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quizEntity = QuizEntity.builder().id(1L).build();
        questionEntity = QuestionEntity.builder().id(1L).question("Sample Question").quiz(quizEntity).build();
        questionDTO = QuestionMapper.toDTO(questionEntity);
    }

    @Test
    void saveQuestion_success() {
        when(quizRepository.findById(anyLong())).thenReturn(quizEntity);

        questionService.save(questionDTO);

        verify(questionRepository, times(1)).save(any(QuestionEntity.class));
    }

    @Test
    void saveQuestion_quizNotFound() {
        when(quizRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> questionService.save(questionDTO)
        );

        assertTrue(thrown.getMessage().contains("Quiz with id " + questionDTO.getQuiz().getId() + " was not found"));
        verify(questionRepository, never()).save(any(QuestionEntity.class));
    }

    @Test
    void findById_success() {
        when(questionRepository.findById(anyLong())).thenReturn(questionEntity);

        QuestionDTO foundQuestion = questionService.findById(1L);

        assertNotNull(foundQuestion);
        assertEquals(questionDTO.getId(), foundQuestion.getId());
    }

    @Test
    void findById_questionNotFound() {
        when(questionRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> questionService.findById(1L)
        );

        assertTrue(thrown.getMessage().contains("Question with id 1 was not found"));
    }

    @Test
    void updateQuestion_success() {
        when(questionRepository.findById(anyLong())).thenReturn(questionEntity);

        questionService.update(questionDTO);

        verify(questionRepository, times(1)).update(any(QuestionEntity.class));
    }

    @Test
    void updateQuestion_questionNotFound() {
        when(questionRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> questionService.update(questionDTO)
        );

        assertTrue(thrown.getMessage().contains("Question with id " + questionDTO.getId() + " was not found"));
        verify(questionRepository, never()).update(any(QuestionEntity.class));
    }

    @Test
    void findAllQuestions() {
        when(questionRepository.findAll()).thenReturn(Collections.singletonList(questionEntity));

        List<QuestionDTO> questions = questionService.findAll();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(1, questions.size());
    }

    @Test
    void deleteQuestion_success() {
        when(questionRepository.findById(anyLong())).thenReturn(questionEntity);

        questionService.delete(1L);

        verify(questionRepository, times(1)).delete(anyLong());
    }

    @Test
    void deleteQuestion_questionNotFound() {
        // Configure mock behavior to return null when anyLong() is called
        when(questionRepository.findById(anyLong())).thenReturn(null);

        // Perform the test
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> questionService.delete(1L)
        );

        // Assert that the correct exception is thrown
        assertTrue(thrown.getMessage().contains("Question with id 1 was not found"));

        // Verify that delete method was never called on the repository
        verify(questionRepository, never()).delete(anyLong());
    }
}
