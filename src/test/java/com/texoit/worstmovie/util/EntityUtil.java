package com.texoit.worstmovie.util;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.*;

public class EntityUtil {

    public static Movie getMovie() {
        Movie movie = new Movie();
        movie.setTitle("Some Movie");
        movie.setWinner(true);
        movie.setYear(2018);
        movie.setId(1L);
        return movie;
    }

    public static MovieDTO getMovieDTO() {
        MovieDTO dto = new MovieDTO();
        dto.setTitle("Some Movie");
        dto.setWinner(true);
        dto.setYear(2018);
        dto.setId(1L);
        return dto;
    }

    public static Producer getProducer() {
        Producer producer = new Producer();
        producer.setId(1L);
        producer.setName("Some Producer");
        return producer;
    }

    public static ProducerDTO getProducerDTO() {
        ProducerDTO dto = new ProducerDTO();
        dto.setName("Some Producer");
        return dto;
    }


    public static Studio getStudio() {
        Studio studio = new Studio();
        studio.setId(1L);
        studio.setName("Some Studio");
        return studio;
    }

    public static StudioDTO getStudioDTO() {
        StudioDTO dto = new StudioDTO();
        dto.setName("Some Studio");
        return dto;
    }

    public static YearsDTO getYearsDTO() {
        YearsDTO yearsDTO = new YearsDTO();
        yearsDTO.add(new YearWinnerCountDTO(2018, 1));
        return yearsDTO;
    }
}
