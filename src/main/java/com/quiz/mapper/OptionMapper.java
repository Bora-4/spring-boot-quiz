package com.quiz.mapper;

import com.quiz.dto.OptionDTO;
import com.quiz.entity.OptionEntity;
import com.quiz.entity.QuestionEntity;

public class OptionMapper {
    private OptionMapper(){
        // private constructor
    }

    public static OptionEntity toEntity(OptionDTO optionDTO, QuestionEntity question){
        OptionEntity option = new OptionEntity();
        option.setId(optionDTO.getId());
        option.setQuestion(question);
        option.setAlternative(optionDTO.getAlternative());
        option.setIsCorrect(optionDTO.getIsCorrect());
        option.setCreatedAt(optionDTO.getCreatedAt());
        option.setUpdatedAt(optionDTO.getUpdatedAt());
        return option;
    }

    public static OptionDTO toDTO(OptionEntity option){
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.setId(option.getId());
        optionDTO.setQuestion(QuestionMapper.toDTO(option.getQuestion()));
        optionDTO.setAlternative(option.getAlternative());
        optionDTO.setIsCorrect(option.getIsCorrect());
        optionDTO.setCreatedAt(option.getCreatedAt());
        optionDTO.setUpdatedAt(option.getUpdatedAt());
        return optionDTO;

    }
}
