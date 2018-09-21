package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.repository.ProducerRepository;
import com.texoit.worstmovie.repository.StudioRepository;
import com.texoit.worstmovie.service.ProducerService;
import com.texoit.worstmovie.service.StudioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ProducerServiceImpl extends BaseServiceImpl<ProducerRepository, Producer> implements ProducerService {

    @Override
    public Producer findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public Producer findOrCreate(String name) {
        Producer producer = this.repository.findByName(name);
        if(Objects.isNull(producer)) {
            producer = new Producer();
            producer.setName(name);
            producer = this.repository.save(producer);
        }
        return producer;
    }
}
