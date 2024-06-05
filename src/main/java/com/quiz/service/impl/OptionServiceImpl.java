package com.quiz.service.impl;

import com.quiz.dto.OptionDTO;
import com.quiz.entity.OptionEntity;
import com.quiz.entity.QuestionEntity;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.mapper.OptionMapper;
import com.quiz.repository.OptionRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.OptionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    public OptionServiceImpl(OptionRepository optionRepository, QuestionRepository questionRepository){
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void save(OptionDTO optionDTO) {
        QuestionEntity question = questionRepository.findById(optionDTO.getQuestion().getId());
        if( question != null){
            OptionEntity option = OptionMapper.toEntity(optionDTO, question);
            optionRepository.save(option);
        } else {
            throw new EntityNotFoundException("Question with id "+ optionDTO.getQuestion().getId()+ " was not found");
        }
    }

    @Override
    public OptionDTO findById(Long id) {
        OptionEntity option = optionRepository.findById(id);
        if( option != null){
            return OptionMapper.toDTO(option);
        } else {
            throw new EntityNotFoundException("Option with id "+ id + " was not found");
        }
    }

    @Override
    public void update(OptionDTO optionDTO) {
        OptionEntity option = optionRepository.findById(optionDTO.getId());
        if( option != null){
            optionRepository.update(option);
        } else {
            throw new EntityNotFoundException("Option with id "+ optionDTO.getId() + " was not found");
        }
    }

    @Override
    public List<OptionDTO> findAll() {
        return optionRepository.findAll().stream()
                .map(OptionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        this.optionRepository.delete(id);
    }
}
