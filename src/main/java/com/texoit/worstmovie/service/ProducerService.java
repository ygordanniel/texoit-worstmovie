package com.texoit.worstmovie.service;

import com.texoit.worstmovie.entity.Producer;

public interface ProducerService extends BaseService<Producer> {

    Producer findByName(String name);
    Producer findOrCreate(String name);
}
