package com.quiz.mapper;

import com.quiz.dto.QuizResultDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.QuizResultEntity;
import com.quiz.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuizResultMapperTest {

    @Test
    public void testToEntity_ValidQuizResultDTO() {
        // Create a sample QuizResultDTO object
        QuizResultDTO quizResultDTO = new QuizResultDTO();
        quizResultDTO.setId(1L);
        quizResultDTO.setScore(90);
        quizResultDTO.setCompletedAt(LocalDateTime.now());

        // Create sample UserEntity and QuizEntity objects
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setId(1L);

        // Map QuizResultDTO to QuizResultEntity
        QuizResultEntity quizResultEntity = QuizResultMapper.toEntity(quizResultDTO, userEntity, quizEntity);

        // Assertions
        assertNotNull(quizResultEntity);
        assertEquals(quizResultDTO.getId(), quizResultEntity.getId());
        assertEquals(userEntity, quizResultEntity.getUser());
        assertEquals(quizEntity, quizResultEntity.getQuiz());
        assertEquals(quizResultDTO.getScore(), quizResultEntity.getScore());
        assertEquals(quizResultDTO.getCompletedAt(), quizResultEntity.getCompletedAt());
    }

    @Test
    public void testToDTO_ValidQuizResultEntity() {
        // Create a sample QuizResultEntity object
        QuizResultEntity quizResultEntity = new QuizResultEntity();
        quizResultEntity.setId(1L);
        quizResultEntity.setScore(90);
        quizResultEntity.setCompletedAt(LocalDateTime.now());

        // Create sample UserEntity and QuizEntity objects
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setId(1L);

        quizResultEntity.setUser(userEntity);
        quizResultEntity.setQuiz(quizEntity);

        // Map QuizResultEntity to QuizResultDTO
        QuizResultDTO quizResultDTO = QuizResultMapper.toDTO(quizResultEntity);

        // Assertions
        assertNotNull(quizResultDTO);
        assertEquals(quizResultEntity.getId(), quizResultDTO.getId());
        assertEquals(userEntity.getId(), quizResultDTO.getUser().getId());
        assertEquals(quizEntity.getId(), quizResultDTO.getQuiz().getId());
        assertEquals(quizResultEntity.getScore(), quizResultDTO.getScore());
        assertEquals(quizResultEntity.getCompletedAt(), quizResultDTO.getCompletedAt());
    }
}
