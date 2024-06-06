package com.quiz.mapper;

import com.quiz.dto.OptionDTO;
import com.quiz.entity.OptionEntity;
import com.quiz.entity.QuestionEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OptionMapperTest {

    @Test
    public void testToEntity_ValidOptionDTO() {
        // Create a sample OptionDTO object
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.setId(1L);
        optionDTO.setAlternative("Option A");
        optionDTO.setIsCorrect(true);
        optionDTO.setCreatedAt(LocalDateTime.now());
        optionDTO.setUpdatedAt(LocalDateTime.now());

        // Create a sample QuestionEntity object
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(1L);

        // Map OptionDTO to OptionEntity
        OptionEntity optionEntity = OptionMapper.toEntity(optionDTO, questionEntity);

        // Assertions
        assertNotNull(optionEntity);
        assertEquals(optionDTO.getId(), optionEntity.getId());
        assertEquals(optionDTO.getAlternative(), optionEntity.getAlternative());
        assertEquals(optionDTO.getIsCorrect(), optionEntity.getIsCorrect());
        assertEquals(optionDTO.getCreatedAt(), optionEntity.getCreatedAt());
        assertEquals(optionDTO.getUpdatedAt(), optionEntity.getUpdatedAt());
        assertEquals(questionEntity, optionEntity.getQuestion());
    }

    @Test
    public void testToDTO_ValidOptionEntity() {
        // Create a sample OptionEntity object
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId(1L);
        optionEntity.setAlternative("Option A");
        optionEntity.setIsCorrect(true);
        optionEntity.setCreatedAt(LocalDateTime.now());
        optionEntity.setUpdatedAt(LocalDateTime.now());

        // Create a sample QuestionEntity object
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(1L);

        optionEntity.setQuestion(questionEntity);

        // Map OptionEntity to OptionDTO
        OptionDTO optionDTO = OptionMapper.toDTO(optionEntity);

        // Assertions
        assertNotNull(optionDTO);
        assertEquals(optionEntity.getId(), optionDTO.getId());
        assertEquals(optionEntity.getAlternative(), optionDTO.getAlternative());
        assertEquals(optionEntity.getIsCorrect(), optionDTO.getIsCorrect());
        assertEquals(optionEntity.getCreatedAt(), optionDTO.getCreatedAt());
        assertEquals(optionEntity.getUpdatedAt(), optionDTO.getUpdatedAt());
        assertEquals(questionEntity.getId(), optionDTO.getQuestion().getId());
    }
}
