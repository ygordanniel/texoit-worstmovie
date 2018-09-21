package com.texoit.worstmovie.repository;

import com.texoit.worstmovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByYear(Integer year);
}
