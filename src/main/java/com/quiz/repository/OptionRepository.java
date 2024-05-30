package com.quiz.repository;

import com.quiz.entity.OptionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository {
    void save(OptionEntity option);
    OptionEntity getOption(Long id);
    void update(OptionEntity option);
    List<OptionEntity> getAllOptions();
    void delete(Long id);
}
