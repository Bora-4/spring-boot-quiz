package com.quiz.mapper;
import com.quiz.dto.QuizDTO;
import com.quiz.dto.UserDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class QuizMapperTest {

    @Test
    void testToEntity_NullQuizDTO() {
        UserEntity user = new UserEntity();
        assertThrows(NullPointerException.class, () -> QuizMapper.toEntity(null, user));
    }

    @Test
    void testToEntity_NullUser() {
        QuizDTO quizDTO = new QuizDTO();
        assertThrows(NullPointerException.class, () -> QuizMapper.toEntity(quizDTO, null));
    }

    @Test
    void testToEntity_ValidQuizDTO() {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(1L);
        quizDTO.setTitle("Sample Quiz");
        quizDTO.setDescription("This is a sample quiz");
        quizDTO.setCreatedAt(LocalDateTime.now());
        quizDTO.setUpdatedAt(LocalDateTime.now());

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");

        QuizEntity quizEntity = QuizMapper.toEntity(quizDTO, user);

        assertNotNull(quizEntity, "QuizEntity should not be null for valid QuizDTO");
        assertEquals(1L, quizEntity.getId());
        assertEquals("Sample Quiz", quizEntity.getTitle());
        assertEquals(user, quizEntity.getUser());
        assertEquals("This is a sample quiz", quizEntity.getDescription());
        assertNotNull(quizEntity.getCreatedAt());
        assertNotNull(quizEntity.getUpdatedAt());
    }

    @Test
    void testToDTO_NullQuizEntity() {
        assertThrows(NullPointerException.class, () -> QuizMapper.toDTO(null));
    }

    @Test
    void testToDTO_ValidQuizEntity() {
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setId(1L);
        quizEntity.setTitle("Sample Quiz");
        quizEntity.setDescription("This is a sample quiz");
        quizEntity.setCreatedAt(LocalDateTime.now());
        quizEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        quizEntity.setUser(user);

        QuizDTO quizDTO = QuizMapper.toDTO(quizEntity);

        assertNotNull(quizDTO, "QuizDTO should not be null for valid QuizEntity");
        assertEquals(1L, quizDTO.getId());
        assertEquals("Sample Quiz", quizDTO.getTitle());
        assertEquals("testuser", quizDTO.getUser().getUsername());
        assertEquals("This is a sample quiz", quizDTO.getDescription());
        assertNotNull(quizDTO.getCreatedAt());
        assertNotNull(quizDTO.getUpdatedAt());
    }
}
