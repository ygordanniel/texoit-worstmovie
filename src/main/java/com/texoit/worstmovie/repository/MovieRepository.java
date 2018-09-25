package com.texoit.worstmovie.repository;

import com.texoit.worstmovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByYear(Integer year);
    List<Movie> findAllByYearAndWinner(Integer year, Boolean winner);

    @Query(value = "SELECT DISTINCT YEAR, COUNT(*) as WINNER_COUNT FROM MOVIE " +
        "WHERE FLG_WINNER IS TRUE " +
        "GROUP BY YEAR HAVING COUNT(*) >= ?1 " +
        "ORDER BY YEAR", nativeQuery = true)
    List<Object[]> findAllYearsByWinnerHigherOne(Integer minCount);
}
