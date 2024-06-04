package com.quiz.repository.impl;

import com.quiz.entity.OptionEntity;
import com.quiz.repository.OptionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepositoryImpl implements OptionRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(OptionEntity option) {
        em.persist(option);
    }

    @Override
    public OptionEntity findById(Long id) {
        return em.find(OptionEntity.class, id);
    }

    @Override
    public void update(OptionEntity option) {
        em.merge(option);
    }

    @Override
    public List<OptionEntity> findAll() {
        return em.createQuery("SELECT o FROM OptionEntity o",
                OptionEntity.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id));
    }
}
