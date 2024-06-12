package com.quiz.service;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.UserEntity;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.mapper.QuizMapper;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.impl.QuizServiceImpl;
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

public class QuizServiceImplTest {

    //In mocking, the dependencies are replaced by closely controlled replacements
    // objects that simulate the behavior of the real ones
    @Mock //creates mock instances of repositories
    private QuizRepository quizRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks // injects mocks to service
    private QuizServiceImpl quizService;

    private QuizDTO quizDTO;

    private QuizEntity quizEntity;
    private UserEntity userEntity;

    @BeforeEach // sets up the test data
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userEntity = UserEntity.builder().id(1L).build();
        quizEntity = QuizEntity.builder().id(1L).title("Sample Quiz").user(userEntity).build();
        quizDTO = QuizMapper.toDTO(quizEntity);
    }

    @Test
    void saveQuiz_success(){


        when(userRepository.findById(anyLong())).thenReturn(userEntity);

        quizService.save(quizDTO);

        verify(quizRepository, times(1)).save(any(QuizEntity.class));

    }

    @Test
    void saveQuiz_userNotFound(){
        when(userRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> quizService.save(quizDTO)
        );

        assertTrue(thrown.getMessage().contains("User with id 1 was not found"));
        verify(quizRepository, never()).save(any(QuizEntity.class));
    }
    @Test
    void findById_success() {
        when(quizRepository.findById(anyLong())).thenReturn(quizEntity);

        QuizDTO foundQuiz = quizService.findById(1L);

        assertNotNull(foundQuiz);
        assertEquals(quizDTO.getId(), foundQuiz.getId());
    }

    @Test
    void findById_quizNotFound() {
        when(quizRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> quizService.findById(1L)
        );

        assertTrue(thrown.getMessage().contains("Quiz with id 1 was not found"));
    }

    @Test
    void updateQuiz_success() {
        when(quizRepository.findById(anyLong())).thenReturn(quizEntity);

        quizService.update(quizDTO);

        verify(quizRepository, times(1)).update(any(QuizEntity.class));
    }

    @Test
    void updateQuiz_quizNotFound() {
        when(quizRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> quizService.update(quizDTO)
        );

        assertTrue(thrown.getMessage().contains("Quiz with id 1 was not found"));
        verify(quizRepository, never()).update(any(QuizEntity.class));
    }

    @Test
    void findAllQuizzes() {
        when(quizRepository.findAll()).thenReturn(Collections.singletonList(quizEntity));

        List<QuizDTO> quizzes = quizService.findAll();

        assertNotNull(quizzes);
        assertFalse(quizzes.isEmpty());
        assertEquals(1, quizzes.size());
    }

    @Test
    void deleteQuiz_success() {
        when(quizRepository.findById(anyLong())).thenReturn(quizEntity);

        quizService.delete(1L);

        verify(quizRepository, times(1)).delete(anyLong());
    }

    @Test
    void deleteQuiz_quizNotFound() {
        when(quizRepository.findById(anyLong())).thenReturn(null);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> quizService.delete(1L)
        );

        assertTrue(thrown.getMessage().contains("Quiz with id 1 was not found"));
        verify(quizRepository, never()).delete(anyLong());
    }
}



