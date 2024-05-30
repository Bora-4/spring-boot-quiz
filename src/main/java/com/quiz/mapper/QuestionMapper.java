package com.quiz.mapper;

import com.quiz.dto.QuestionDTO;
import com.quiz.entity.QuestionEntity;
import com.quiz.entity.QuizEntity;

public class QuestionMapper {
    private QuestionMapper(){
        // private constructor
    }

    public static QuestionEntity toEntity(QuestionDTO questionDTO, QuizEntity quiz){
        QuestionEntity question = new QuestionEntity();
        question.setId(questionDTO.getId());
        question.setQuestion(questionDTO.getQuestion());
        question.setCreatedAt(questionDTO.getCreatedAt());
        question.setUpdatedAt(questionDTO.getUpdatedAt());
        question.setQuiz(quiz);
        return question;
    }
    public static QuestionDTO toDTO(QuestionEntity question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setCreatedAt(question.getCreatedAt());
        questionDTO.setUpdatedAt(question.getUpdatedAt());
        questionDTO.setQuiz(QuizMapper.toDTO(question.getQuiz()));
        return questionDTO;
    }
}
