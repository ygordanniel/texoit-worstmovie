package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.dto.ProducerIntervalAwardDTO;
import com.texoit.worstmovie.entity.dto.ProducerMinMaxAwardDTO;
import com.texoit.worstmovie.repository.ProducerRepository;
import com.texoit.worstmovie.service.ProducerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProducerServiceImpl extends BaseServiceImpl<ProducerRepository, Producer> implements ProducerService {

    public ProducerServiceImpl(ProducerRepository repository) {
        super(repository);
    }

    @Override
    public Producer findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public Producer findOrCreate(String name) {
        Producer producer = this.repository.findByName(name);
        if(Objects.isNull(producer)) {
            producer = new Producer();
            producer.setName(name);
            producer = this.repository.save(producer);
        }
        return producer;
    }

    @Override
    public List<Producer> findAllByMovieWinner() {
        return this.repository.findAllByMovieWinner();
    }

    @Override
    public ProducerMinMaxAwardDTO findMinMaxAward() {
        List<Producer> allByMovieWinner = this.findAllByMovieWinner();
        Map<Long, ProducerIntervalAwardDTO> mapByInterval = new HashMap<>();
        for (
                Producer producer :
                allByMovieWinner
                        .stream()
                        .filter(producer -> producer.getMovies().size() > 1).collect(Collectors.toList())
        ) {
            producer.getMovies().sort(Comparator.comparingInt(Movie::getYear));
            Integer previousWin = -1, followingWin = -1;
            Long interval = -1L;
            for (Integer year : producer.getMovies().stream().map(Movie::getYear).collect(Collectors.toList())) {
                if(followingWin < 0 || followingWin > year) {
                    previousWin = year;
                }
                if(previousWin > -1 || followingWin < year) {
                    previousWin = followingWin;
                    followingWin = year;
                }
                DateTimeFormatter formmatter = new DateTimeFormatterBuilder()
                        .appendPattern("yyyy")
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .toFormatter();
                if(previousWin > -1 && followingWin > -1) {
                    LocalDate previousWinLcalDate = LocalDate.parse(previousWin.toString(), formmatter);
                    LocalDate followingWinLcalDate = LocalDate.parse(followingWin.toString(), formmatter);
                    interval = previousWinLcalDate.until(followingWinLcalDate, ChronoUnit.YEARS);
                }
            }
            if(previousWin < followingWin) {
                ProducerIntervalAwardDTO dto = new ProducerIntervalAwardDTO();
                dto.setProducer(producer.getName());
                dto.setPreviousWin(previousWin);
                dto.setFollowingWin(followingWin);
                dto.setInterval(interval);
                mapByInterval.put(interval, dto);
            }
        }
        ProducerMinMaxAwardDTO dto = new ProducerMinMaxAwardDTO();
        dto.setMin(mapByInterval.get(Collections.min(mapByInterval.keySet())));
        dto.setMax(mapByInterval.get(Collections.max(mapByInterval.keySet())));
        return dto;
    }
}
