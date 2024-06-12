package com.quiz.mapper;
import com.quiz.dto.QuestionDTO;
import com.quiz.entity.QuestionEntity;
import com.quiz.entity.QuizEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class QuestionMapperTest {

    @Test
    void testToEntity_NullQuestionDTO() {
        QuizEntity quiz = new QuizEntity();
        assertThrows(NullPointerException.class, () -> QuestionMapper.toEntity(null, quiz));
    }

    @Test
    void testToEntity_NullQuiz() {
        QuestionDTO questionDTO = new QuestionDTO();
        assertThrows(NullPointerException.class, () -> QuestionMapper.toEntity(questionDTO, null));
    }

    @Test
    void testToEntity_ValidQuestionDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(1L);
        questionDTO.setQuestion("Sample Question");
        questionDTO.setCreatedAt(LocalDateTime.now());
        questionDTO.setUpdatedAt(LocalDateTime.now());

        QuizEntity quiz = new QuizEntity();
        quiz.setId(1L);
        quiz.setTitle("Sample Quiz");

        QuestionEntity questionEntity = QuestionMapper.toEntity(questionDTO, quiz);

        assertNotNull(questionEntity, "QuestionEntity should not be null for valid QuestionDTO");
        assertEquals(1L, questionEntity.getId());
        assertEquals("Sample Question", questionEntity.getQuestion());
        assertEquals(quiz, questionEntity.getQuiz());
        assertNotNull(questionEntity.getCreatedAt());
        assertNotNull(questionEntity.getUpdatedAt());
    }

    @Test
    void testToDTO_NullQuestionEntity() {
        assertThrows(NullPointerException.class, () -> QuestionMapper.toDTO(null));
    }

    @Test
    void testToDTO_ValidQuestionEntity() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(1L);
        questionEntity.setQuestion("Sample Question");
        questionEntity.setCreatedAt(LocalDateTime.now());
        questionEntity.setUpdatedAt(LocalDateTime.now());

        QuizEntity quiz = new QuizEntity();
        quiz.setId(1L);
        quiz.setTitle("Sample Quiz");
        questionEntity.setQuiz(quiz);

        QuestionDTO questionDTO = QuestionMapper.toDTO(questionEntity);

        assertNotNull(questionDTO, "QuestionDTO should not be null for valid QuestionEntity");
        assertEquals(1L, questionDTO.getId());
        assertEquals("Sample Question", questionDTO.getQuestion());
        assertEquals("Sample Quiz", questionDTO.getQuiz().getTitle());
        assertNotNull(questionDTO.getCreatedAt());
        assertNotNull(questionDTO.getUpdatedAt());
    }
}
