package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.BasicEntity;
import com.texoit.worstmovie.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public abstract class BaseServiceImpl<R extends JpaRepository<EN, Long>, EN extends BasicEntity> implements BaseService<EN> {

    @Autowired
    R repository;

    @Override
    public EN save(EN entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<EN> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<EN> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<EN> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
