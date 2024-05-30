package com.quiz.mapper;

import com.quiz.dto.QuizResultDTO;
import com.quiz.entity.QuizEntity;
import com.quiz.entity.QuizResultEntity;
import com.quiz.entity.UserEntity;

public class QuizResultMapper {
    private QuizResultMapper(){
        // private constructor
    }
    public static QuizResultEntity toEntity(QuizResultDTO quizResultDTO, UserEntity user, QuizEntity quiz){
        QuizResultEntity quizResult = new QuizResultEntity();
        quizResult.setId(quizResultDTO.getId());
        quizResult.setUser(user);
        quizResult.setQuiz(quiz);
        quizResult.setScore(quizResultDTO.getScore());
        quizResult.setCompletedAt(quizResultDTO.getCompletedAt());
        return quizResult;
    }

    public static QuizResultDTO toDTO(QuizResultEntity quizResult){
        QuizResultDTO quizResultDTO = new QuizResultDTO();
        quizResultDTO.setId(quizResult.getId());
        quizResultDTO.setUser(UserMapper.toDTO(quizResult.getUser()));
        quizResultDTO.setQuiz(QuizMapper.toDTO(quizResult.getQuiz()));
        quizResultDTO.setScore(quizResult.getScore());
        quizResultDTO.setCompletedAt(quizResult.getCompletedAt());
        return quizResultDTO;
    }

}
