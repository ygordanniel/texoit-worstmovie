package com.texoit.worstmovie.service;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.dto.MovieDTO;
import com.texoit.worstmovie.entity.dto.YearsDTO;
import com.texoit.worstmovie.exception.BusinessException;
import com.texoit.worstmovie.exception.MovieIsWinnerException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService extends BaseService<Movie> {

    List<MovieDTO> findAllDTO();

    MovieDTO findOneDTO(Long id);

    List<MovieDTO> findAllByYear(Integer year);

    List<MovieDTO> findAllByYearAndWinner(Integer year, Boolean winner);

    YearsDTO findAllYearsByWinnerCount(Integer minCount);

    void delete(Long id) throws BusinessException;

    Integer importFromCsv(MultipartFile[] multipartFile) throws BusinessException, IOException;
}
