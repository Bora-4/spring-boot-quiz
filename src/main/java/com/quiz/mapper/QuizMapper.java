package com.quiz.mapper;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.UserEntity;

public class QuizMapper {
    private QuizMapper() {
        // private constructor
    }

    public static QuizEntity toEntity(QuizDTO quizDTO, UserEntity user) {
        if (quizDTO == null || user == null) {
            throw new NullPointerException("QuizDTO and UserEntity must not be null");
        }

        QuizEntity quiz = new QuizEntity();
        quiz.setId(quizDTO.getId());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setUser(user);
        quiz.setDescription(quizDTO.getDescription());
        quiz.setCreatedAt(quizDTO.getCreatedAt());
        quiz.setUpdatedAt(quizDTO.getUpdatedAt());
        return quiz;
    }

    public static QuizDTO toDTO(QuizEntity quiz) {
        if (quiz == null) {
            throw new NullPointerException("QuizEntity must not be null");
        }

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setDescription(quiz.getDescription());
        quizDTO.setCreatedAt(quiz.getCreatedAt());
        quizDTO.setUpdatedAt(quiz.getUpdatedAt());

        // Check if the UserEntity is not null before mapping it to UserDTO
        if (quiz.getUser() != null) {
            quizDTO.setUser(UserMapper.toDTO(quiz.getUser()));
        } else {
            quizDTO.setUser(null);
        }

        return quizDTO;
    }

}
