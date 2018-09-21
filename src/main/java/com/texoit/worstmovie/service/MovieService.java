package com.texoit.worstmovie.service;

import com.texoit.worstmovie.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService extends BaseService<Movie> {

    List<Movie> findAllByYear(Integer year);

    Integer importFromCsv(MultipartFile[] multipartFile);
}
