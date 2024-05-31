package com.quiz.service;

import com.quiz.dto.OptionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OptionService {
    void save(OptionDTO optionDTO);
    OptionDTO findById(Long id);
    void update(OptionDTO optionDTO);
    List<OptionDTO> findAll();
    void delete(Long id);
}
