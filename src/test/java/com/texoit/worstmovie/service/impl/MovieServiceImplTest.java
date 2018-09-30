package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.dto.MovieDTO;
import com.texoit.worstmovie.entity.dto.YearsDTO;
import com.texoit.worstmovie.entity.mapper.MovieMapper;
import com.texoit.worstmovie.entity.mapper.YearsMapper;
import com.texoit.worstmovie.exception.BusinessException;
import com.texoit.worstmovie.exception.MovieIsWinnerException;
import com.texoit.worstmovie.exception.NoFileException;
import com.texoit.worstmovie.exception.NotACSVException;
import com.texoit.worstmovie.repository.MovieRepository;
import com.texoit.worstmovie.service.ProducerService;
import com.texoit.worstmovie.service.StudioService;
import com.texoit.worstmovie.util.EntityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

    @Mock
    private StudioService studioService;
    @Mock
    private ProducerService producerService;
    @Mock
    private MovieMapper mapper;
    @Mock
    private YearsMapper yearsMapper;
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    @Before
    public void init() {
        when(mapper.movieToMovieDTO(any(Movie.class))).thenReturn(EntityUtil.getMovieDTO());
    }

    @Test
    public void searchAllMoviesByYearFindOne() {
        when(movieRepository.findAllByYear(anyInt())).thenReturn(singletonList(EntityUtil.getMovie()));
        List<MovieDTO> allByYear = this.movieService.findAllByYear(2018);
        assertNotNull(allByYear);
        assertTrue(allByYear.size() == 1);
        assertEquals(allByYear.get(0).getId(), EntityUtil.getMovieDTO().getId());
    }

    @Test
    public void searchAllMoviesByYearFindNone() {
        when(movieRepository.findAllByYear(anyInt())).thenReturn(emptyList());
        List<MovieDTO> allByYear = this.movieService.findAllByYear(2018);
        assertNotNull(allByYear);
        assertTrue(allByYear.isEmpty());
    }

    @Test
    public void searchAllMoviesByYearAndWinnerFindOne() {
        when(movieRepository.findAllByYearAndWinner(anyInt(), anyBoolean())).thenReturn(singletonList(EntityUtil.getMovie()));
        List<MovieDTO> allByYear = this.movieService.findAllByYearAndWinner(2018, true);
        assertNotNull(allByYear);
        assertTrue(allByYear.size() == 1);
        assertEquals(allByYear.get(0).getId(), EntityUtil.getMovieDTO().getId());
    }

    @Test
    public void searchAllMoviesByYearAndWinnerFindNone() {
        when(movieRepository.findAllByYearAndWinner(anyInt(), anyBoolean())).thenReturn(emptyList());
        List<MovieDTO> allByYear = this.movieService.findAllByYearAndWinner(2018, true);
        assertNotNull(allByYear);
        assertTrue(allByYear.isEmpty());
    }

    @Test
    public void searchAllWinnersByWInnerCountFindOne() {
        when(movieRepository.findAllYearsByWinnerCount(anyInt())).thenReturn(singletonList(new Object[]{2018, 1}));
        when(yearsMapper.objListToYearsDTO(anyList())).thenReturn(EntityUtil.getYearsDTO());
        YearsDTO allYearsByWinnerCount = this.movieService.findAllYearsByWinnerCount(1);
        assertNotNull(allYearsByWinnerCount);
        assertTrue(allYearsByWinnerCount.getYears().size() == 1);
    }

    @Test(expected = MovieIsWinnerException.class)
    public void deleteMovieThrowsMovieIsWinnerException() throws BusinessException {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(EntityUtil.getMovie()));
        movieService.delete(1L);
    }

    @Test
    public void deleteMovieOkn() throws BusinessException {
        Movie movie = EntityUtil.getMovie();
        movie.setWinner(false);
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        movieService.delete(1L);
    }

    @Test(expected = NoFileException.class)
    public void importCsvThrowsNoFileException() throws BusinessException, IOException {
        movieService.importFromCsv(null);
    }

    @Test(expected = NotACSVException.class)
    public void importCsvThrowsNotACSVException() throws BusinessException, IOException {
        MultipartFile multipartFile = new MockMultipartFile("csv", "movies.pdf", "application/octet-stream", new byte[]{});
        movieService.importFromCsv(new MultipartFile[]{multipartFile});
    }

    @Test
    public void importCsvOk() throws BusinessException, IOException {
        InputStream csvFileStream = getClass().getClassLoader().getResourceAsStream("movielist.csv");
        MultipartFile multipartFile = new MockMultipartFile("movie.csv", "movielist.csv", "text/csv", csvFileStream);
        movieService.importFromCsv(new MultipartFile[]{multipartFile});
    }
}