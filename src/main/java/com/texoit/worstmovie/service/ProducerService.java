package com.texoit.worstmovie.service;

import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.dto.ProducerDTO;
import com.texoit.worstmovie.entity.dto.ProducerMinMaxAwardDTO;

import java.util.List;

public interface ProducerService extends BaseService<Producer> {

    ProducerDTO findByName(String name);
    Producer findOrCreate(String name);
    List<Producer> findAllByMovieWinner();
    ProducerMinMaxAwardDTO findMinMaxAward();
}
