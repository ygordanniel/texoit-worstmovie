package com.texoit.worstmovie.repository;

import com.texoit.worstmovie.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);

    @Query("SELECT pd FROM Producer pd JOIN pd.movies mv WHERE mv.winner IS TRUE")
    List<Producer> findAllByMovieWinner();
}
