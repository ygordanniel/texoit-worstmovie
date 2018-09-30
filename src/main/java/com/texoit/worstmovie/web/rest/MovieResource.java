package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.dto.MovieDTO;
import com.texoit.worstmovie.entity.dto.ResponseDTO;
import com.texoit.worstmovie.entity.dto.YearsDTO;
import com.texoit.worstmovie.exception.BusinessException;
import com.texoit.worstmovie.exception.MovieIsWinnerException;
import com.texoit.worstmovie.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/movie")
public class MovieResource {

    private MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/year")
    public ResponseEntity<ResponseDTO<List<MovieDTO>>> findAllByYear(@RequestParam Integer year, @RequestParam(required = false) Boolean winner) {
        List<MovieDTO> found;
        if(Objects.isNull(winner)) {
            found = this.movieService.findAllByYear(year);
        } else {
            found = this.movieService.findAllByYearAndWinner(year, winner);
        }
        return ResponseEntity.ok(new ResponseDTO<>(found));
    }

    @GetMapping("/year/winner-count")
    public ResponseEntity<ResponseDTO<YearsDTO>> findAllYearsByWinnerHigherOne(@RequestParam Integer minCount) {
        return ResponseEntity.ok(new ResponseDTO<>(this.movieService.findAllYearsByWinnerHigherOne(minCount)));
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(this.movieService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        try {
            this.movieService.delete(id);
            return ResponseEntity.ok().build();
        } catch (BusinessException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(ex.getExceptionEnum()));
        }
    }

    @PutMapping("/import/csv")
    public ResponseEntity<ResponseDTO> importFromCsv(@RequestPart(name = "csv", required = false) MultipartFile[] csv) {
        try {
            this.movieService.importFromCsv(csv);
            return ResponseEntity.created(new URI("http://localhost:8080/api/movie")).build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getExceptionEnum()));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(e.getMessage()));
        }
    }
}
