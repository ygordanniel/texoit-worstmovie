package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.dto.MovieDTO;
import com.texoit.worstmovie.entity.dto.ResponseDTO;
import com.texoit.worstmovie.entity.dto.YearsDTO;
import com.texoit.worstmovie.exception.*;
import com.texoit.worstmovie.service.MovieService;
import com.texoit.worstmovie.util.EntityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieResourceTest {

    @Mock
    private MovieService service;
    @InjectMocks
    private MovieResource resource;

    @Before
    public void init() {
    }

    @Test
    public void searchAllMoviesByYearWithoutWinner() {
        when(service.findAllByYear(anyInt())).thenReturn(singletonList(EntityUtil.getMovieDTO()));
        ResponseEntity<ResponseDTO<List<MovieDTO>>> response = resource.findAllByYear(2018, null);
        statusOkBodyNotNull(response);
        assertTrue(response.getBody().getContent().size() == 1);
        assertEquals(EntityUtil.getMovieDTO().getId(), response.getBody().getContent().get(0).getId());
    }

    @Test
    public void searchAllMoviesByYearWithWinner() {
        when(service.findAllByYearAndWinner(anyInt(), anyBoolean())).thenReturn(singletonList(EntityUtil.getMovieDTO()));
        ResponseEntity<ResponseDTO<List<MovieDTO>>> response = resource.findAllByYear(2018, true);
        statusOkBodyNotNull(response);
        assertTrue(response.getBody().getContent().size() == 1);
        assertEquals(EntityUtil.getMovieDTO().getId(), response.getBody().getContent().get(0).getId());
    }

    @Test
    public void searchMovieYearsByWinnerCount() {
        when(service.findAllYearsByWinnerCount(anyInt())).thenReturn(EntityUtil.getYearsDTO());
        ResponseEntity<ResponseDTO<YearsDTO>> response = resource.findAllYearsByWinnerCount(2);
        statusOkBodyNotNull(response);
        assertTrue(response.getBody().getContent().getYears().size() == 1);
        assertEquals(new Integer(2018), response.getBody().getContent().getYears().get(0).getYear());
        assertEquals(new Integer(1), response.getBody().getContent().getYears().get(0).getWinnerCount());
    }

    @Test
    public void deleteMovieOk() {
        ResponseEntity<ResponseDTO> response = resource.delete(1L);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void deleteMovieThrowsMovieIsWinnerException() throws BusinessException {
        doThrow(new MovieIsWinnerException()).when(service).delete(anyLong());
        ResponseEntity<ResponseDTO> response = resource.delete(1L);
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        assertEquals(EnumBusinessException.MOVIE_IS_WINNER.getCode(), response.getBody().getError().getCode());
    }

    @Test
    public void importCsvThrowsNoFileException() throws IOException, BusinessException {
        when(service.importFromCsv(any())).thenThrow(new NoFileException());
        ResponseEntity<ResponseDTO> response = resource.importFromCsv(null, null);
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        assertEquals(EnumBusinessException.NO_FILE.getCode(), response.getBody().getError().getCode());
    }

    @Test
    public void importCsvThrowsNotACSVException() throws BusinessException, IOException {
        when(service.importFromCsv(any())).thenThrow(new NotACSVException());
        MultipartFile multipartFile = new MockMultipartFile("csv", "movies.pdf", "application/octet-stream", new byte[]{});
        ResponseEntity<ResponseDTO> response = resource.importFromCsv(new MultipartFile[]{multipartFile}, null);
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        assertEquals(EnumBusinessException.NOT_A_CSV.getCode(), response.getBody().getError().getCode());
    }

    @Test
    public void importCsvOk() throws IOException {
        InputStream csvFileStream = getClass().getClassLoader().getResourceAsStream("movielist.csv");
        MultipartFile multipartFile = new MockMultipartFile("movie.csv", "movielist.csv", "text/csv", csvFileStream);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("");
        ResponseEntity<ResponseDTO> response = resource.importFromCsv(new MultipartFile[]{multipartFile}, request);
        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
    }

    private void statusOkBodyNotNull(ResponseEntity responseEntity) {
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertNotNull(responseEntity.getBody());
    }
}