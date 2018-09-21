package com.texoit.worstmovie.repository;

import com.texoit.worstmovie.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    Studio findByName(String name);
}
