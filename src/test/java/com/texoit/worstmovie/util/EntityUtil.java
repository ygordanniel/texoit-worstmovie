package com.texoit.worstmovie.util;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.*;
import com.texoit.worstmovie.service.impl.StudioServiceImplTest;

import java.util.Arrays;

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

    public static Producer getProducerMin() {
        Producer min = new Producer();
        min.setId(1L);
        min.setName("Some Producer Min");
        Movie movieMin1 = new Movie();
        movieMin1.setWinner(true);
        movieMin1.setId(1L);
        movieMin1.setYear(2017);
        movieMin1.setTitle("Some Movie Min One");
        Movie movieMin2 = new Movie();
        movieMin2.setWinner(true);
        movieMin2.setId(2L);
        movieMin2.setYear(2018);
        movieMin2.setTitle("Some Movie Min Two");
        min.setMovies(Arrays.asList(movieMin1, movieMin2));
        return min;
    }

    public static Producer getProducerBetweenMedium() {
        Producer betweenMedium = new Producer();
        betweenMedium.setId(2L);
        betweenMedium.setName("Some Producer Between Medium");
        Movie movieBetweenMedium1 = new Movie();
        movieBetweenMedium1.setWinner(true);
        movieBetweenMedium1.setId(3L);
        movieBetweenMedium1.setYear(2010);
        movieBetweenMedium1.setTitle("Some Movie Between Medium One");
        Movie movieBetweenMedium2 = new Movie();
        movieBetweenMedium2.setWinner(true);
        movieBetweenMedium2.setId(4L);
        movieBetweenMedium2.setYear(2015);
        movieBetweenMedium2.setTitle("Some Movie Between Medium Two");
        betweenMedium.setMovies(Arrays.asList(movieBetweenMedium1, movieBetweenMedium2));
        return betweenMedium;
    }

    public static Producer getProducerMax() {
        Producer max = new Producer();
        max.setId(3L);
        max.setName("Some Producer Min");
        Movie movieMax1 = new Movie();
        movieMax1.setWinner(true);
        movieMax1.setId(5L);
        movieMax1.setYear(1991);
        movieMax1.setTitle("Some Movie Max One");
        Movie movieMax2 = new Movie();
        movieMax2.setWinner(true);
        movieMax2.setId(6L);
        movieMax2.setYear(2017);
        movieMax2.setTitle("Some Movie Max Two");
        max.setMovies(Arrays.asList(movieMax1, movieMax2));
        return max;
    }

    public static Producer getProducerBetweenLong() {
        Producer betweenLong = new Producer();
        betweenLong.setId(4L);
        betweenLong.setName("Some Producer Between Long");
        Movie movieBetweenLong1 = new Movie();
        movieBetweenLong1.setWinner(true);
        movieBetweenLong1.setId(3L);
        movieBetweenLong1.setYear(1997);
        movieBetweenLong1.setTitle("Some Movie Between Long One");
        Movie movieBetweenLong2 = new Movie();
        movieBetweenLong2.setWinner(true);
        movieBetweenLong2.setId(4L);
        movieBetweenLong2.setYear(2014);
        movieBetweenLong2.setTitle("Some Movie Between Long Two");
        betweenLong.setMovies(Arrays.asList(movieBetweenLong1, movieBetweenLong2));
        return betweenLong;
    }

    public static StudiosWinCountDTO getStudiosWinCountDTO() {
        StudiosWinCountDTO studiosWinCountDTO = new StudiosWinCountDTO();
        studiosWinCountDTO.add(new StudioWinCountDTO(StudioServiceImplTest.SOME_STUDIO_NAME, 3));
        return studiosWinCountDTO;
    }

    public static ProducerMinMaxAwardDTO getProducerMinMaxAwardDTO() {
        ProducerMinMaxAwardDTO dto = new ProducerMinMaxAwardDTO();
        ProducerIntervalAwardDTO min = new ProducerIntervalAwardDTO();
        min.setProducer("Some Producer");
        min.setInterval(1L);
        min.setPreviousWin(2017);
        min.setFollowingWin(2018);
        ProducerIntervalAwardDTO max = new ProducerIntervalAwardDTO();
        max.setProducer("Some Other Producer");
        max.setInterval(6L);
        max.setPreviousWin(2010);
        max.setFollowingWin(2016);
        dto.setMin(min);
        dto.setMax(max);
        return dto;
    }
}
