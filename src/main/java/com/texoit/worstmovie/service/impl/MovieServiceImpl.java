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

    public MovieServiceImpl(StudioService studioService, ProducerService producerService, MovieMapper mapper, YearsMapper yearsMapper, MovieRepository repository) {
        super(repository);
        this.studioService = studioService;
        this.producerService = producerService;
        this.mapper = mapper;
        this.yearsMapper = yearsMapper;
    }

    @Override
    public List<MovieDTO> findAllDTO() {
        return findAll()
            .stream()
            .map(mapper::movieToMovieDTO)
            .collect(Collectors.toList());
    }

    @Override
    public MovieDTO findOneDTO(Long id) {
        Optional<Movie> one = findById(id);
        if(one.isPresent()) {
            return mapper.movieToMovieDTO(one.get());
        }
        return null;
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
    public YearsDTO findAllYearsByWinnerCount(Integer minCount) {
        List<Object[]> found = this.repository.findAllYearsByWinnerCount(minCount);
        return yearsMapper.objListToYearsDTO(found);
    }

    @Override
    public void delete(Long id) throws BusinessException {
        Optional<Movie> found = this.findById(id);
        if(found.isPresent()) {
            if(!found.get().getWinner()) {
                deleteById(found.get().getId());
            } else {
                throw BusinessExceptionFactory.buildException(EnumBusinessException.MOVIE_IS_WINNER);
            }
        }
    }

    @Override
    public Integer importFromCsv(MultipartFile[] multipartFile) throws BusinessException, IOException {
        String[] HEADERS = {"year", "title", "studios", "producers", "winner"};
        validateCsvFile(multipartFile);
        for (MultipartFile multipart : multipartFile) {
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
            throw BusinessExceptionFactory.buildException(EnumBusinessException.NO_FILE);
        }
        if(multipartFile.length > 1) {
            throw BusinessExceptionFactory.buildException(EnumBusinessException.MORE_THAN_ONE_FILE);
        }
        for (MultipartFile file : multipartFile) {
            if(!file.getOriginalFilename().contains(".csv")) {
                throw BusinessExceptionFactory.buildException(EnumBusinessException.NOT_A_CSV);
            }
        }
    }
}
