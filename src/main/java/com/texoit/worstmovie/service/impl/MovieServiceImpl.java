package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.repository.MovieRepository;
import com.texoit.worstmovie.service.MovieService;
import com.texoit.worstmovie.service.ProducerService;
import com.texoit.worstmovie.service.StudioService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl extends BaseServiceImpl<MovieRepository, Movie> implements MovieService {

    private StudioService studioService;
    private ProducerService producerService;

    public MovieServiceImpl(StudioService studioService, ProducerService producerService) {
        this.studioService = studioService;
        this.producerService = producerService;
    }

    @Override
    public List<Movie> findAllByYear(Integer year) {
        return this.repository.findAllByYear(year);
    }

    @Override
    public Integer importFromCsv(MultipartFile[] multipartFile) {
        String[] HEADERS = {"year", "title", "studios", "producers", "winner"};
        for (MultipartFile multipart : multipartFile) {
            try {
                CSVParser parse = CSVParser.parse(multipart.getInputStream(),
                    StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.withDelimiter(';').withHeader(HEADERS).withFirstRecordAsHeader());
                parse.getRecords().forEach(record -> {
                    Movie movie = new Movie();
                    movie.setTitle(record.get("title"));
                    movie.setYear(Integer.parseInt(record.get("year")));
                    movie.setWin(!record.get("winner").trim().isEmpty());
//                    ",|^and$"
//                    https://www.thoughts-on-java.org/hibernate-tips-map-bidirectional-many-many-association/
                    movie.setProducer(this.producerService.findOrCreate(record.get("producers")));
                    movie.setStudio(this.studioService.findOrCreate(record.get("studios")));
                    this.save(movie);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
