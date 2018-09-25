package com.texoit.worstmovie.repository;

import com.texoit.worstmovie.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    Studio findByName(String name);

    @Query(value = "SELECT DISTINCT std.DSC_NAME, COUNT(mv.SEQ_MOVIE) AS WIN_COUNT FROM STUDIO std " +
        "LEFT JOIN MOVIE_STUDIO mvstd ON std.SEQ_DOMAIN = mvstd.SEQ_DOMAIN " +
        "LEFT JOIN MOVIE mv ON mvstd.SEQ_MOVIE = mv.SEQ_MOVIE " +
        "WHERE mv.FLG_WINNER IS TRUE " +
        "GROUP BY std.DSC_NAME " +
        "ORDER BY WIN_COUNT DESC", nativeQuery = true)
    List<Object[]> findStudioAndWinCount();
}
