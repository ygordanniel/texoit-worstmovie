package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.MovieDTO;
import com.texoit.worstmovie.entity.dto.YearsDTO;
import com.texoit.worstmovie.entity.mapper.MovieMapper;
import com.texoit.worstmovie.entity.mapper.YearsMapper;
import com.texoit.worstmovie.exception.*;
import com.texoit.worstmovie.repository.MovieRepository;
import com.texoit.worstmovie.service.MovieService;
import com.texoit.worstmovie.service.ProducerService;
import com.texoit.worstmovie.service.StudioService;
import com.texoit.worstmovie.util.StringUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl extends BaseServiceImpl<MovieRepository, Movie> implements MovieService {

    //http://localhost:8080/h2-console

    public static final String SPLIT_REGEX = ",|(?:^|\\W)and(?:$|\\W)";

    private StudioService studioService;
    private ProducerService producerService;
    private MovieMapper mapper;
    private YearsMapper yearsMapper;

    public MovieServiceImpl(StudioService studioService, ProducerService producerService, MovieMapper mapper, YearsMapper yearsMapper) {
        this.studioService = studioService;
        this.producerService = producerService;
        this.mapper = mapper;
        this.yearsMapper = yearsMapper;
    }

    @Override
    public List<MovieDTO> findAllByYear(Integer year) {
        return this.repository.findAllByYear(year)
            .stream()
            .map(mapper::movieToMovieDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findAllByYearAndWinner(Integer year, Boolean winner) {
        return this.repository.findAllByYearAndWinner(year, winner)
            .stream()
            .map(mapper::movieToMovieDTO)
            .collect(Collectors.toList());
    }

    @Override
    public YearsDTO findAllYearsByWinnerHigherOne(Integer minCount) {
        List<Object[]> found = this.repository.findAllYearsByWinnerHigherOne(minCount);
        return yearsMapper.objListToYearsDTO(found);
    }

    @Override
    public void delete(Long id) throws BusinessException {
        Optional<Movie> found = this.findById(id);
        if(found.isPresent()) {
            if(!found.get().getWinner()) {
                this.repository.delete(found.get());
            } else {
                throw BusinessExceptionFactory.buildException(BusinessExceptionEnum.MOVIE_IS_WINNER);
            }
        }
    }

    @Override
    public Integer importFromCsv(MultipartFile[] multipartFile) throws BusinessException {
        String[] HEADERS = {"year", "title", "studios", "producers", "winner"};
        validateCsvFile(multipartFile);
        for (MultipartFile multipart : multipartFile) {
            try {
                CSVParser parse = CSVParser.parse(multipart.getInputStream(),
                    StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.withDelimiter(';').withHeader(HEADERS).withFirstRecordAsHeader());
                parse.getRecords().forEach(record -> {
                    Movie movie = new Movie();
                    movie.setTitle(record.get("title"));
                    movie.setYear(Integer.parseInt(record.get("year")));
                    movie.setWinner(!record.get("winner").trim().isEmpty());
                    this.processRelationships(movie, record);
                    this.save(movie);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void processRelationships(Movie movie, CSVRecord record) {
        this.processProducers(movie, record);
        this.processStudios(movie, record);
    }

    private void processProducers(Movie movie, CSVRecord record) {
        List<Producer> producers = new ArrayList<>();
        List<String> producersNames = StringUtil.splitStringGetMappedValidValues(record.get("producers"),
                SPLIT_REGEX,
                str -> !str.trim().isEmpty(),
            String::trim);
        producersNames.forEach(producerName -> producers.add(this.producerService.findOrCreate(producerName)));
        movie.setProducers(producers);
    }

    private void processStudios(Movie movie, CSVRecord record) {
        List<Studio> studios = new ArrayList<>();
        List<String> studiosNames = StringUtil.splitStringGetMappedValidValues(record.get("studios"),
                SPLIT_REGEX,
                str -> !str.trim().isEmpty(),
                String::trim);
        studiosNames.forEach(studioName -> studios.add(this.studioService.findOrCreate(studioName)));
        movie.setStudios(studios);
    }

    private void validateCsvFile(MultipartFile[] multipartFile) throws BusinessException {
        if(Objects.isNull(multipartFile)) {
            throw BusinessExceptionFactory.buildException(BusinessExceptionEnum.NO_FILE);
        }
        if(multipartFile.length > 1) {
            throw BusinessExceptionFactory.buildException(BusinessExceptionEnum.MORE_THAN_ONE_CSV);
        }
        for (MultipartFile file : multipartFile) {
            if(!file.getName().contains(".csv")) {
                throw BusinessExceptionFactory.buildException(BusinessExceptionEnum.NOT_A_CSV);
            }
        }
    }
}
