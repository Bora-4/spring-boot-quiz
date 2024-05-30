package com.quiz.mapper;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.UserEntity;

public class QuizMapper {
    private QuizMapper(){
        //private constructor
    }
    public static QuizEntity toEntity(QuizDTO quizDTO, UserEntity user){
        QuizEntity quiz = new QuizEntity();
        quiz.setId(quizDTO.getId());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setUser(user);
        quiz.setDescription(quizDTO.getDescription());
        quiz.setCreatedAt(quizDTO.getCreatedAt());
        quiz.setUpdatedAt(quizDTO.getUpdatedAt());
        return quiz;

    }

    public static QuizDTO toDTO(QuizEntity quiz){
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setUser(UserMapper.toDTO(quiz.getUser()));
        quizDTO.setDescription(quiz.getDescription());
        quizDTO.setCreatedAt(quiz.getCreatedAt());
        quizDTO.setUpdatedAt(quiz.getUpdatedAt());
        return quizDTO;
    }
}
