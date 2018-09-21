package com.texoit.worstmovie.repository;

import com.texoit.worstmovie.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);
}
