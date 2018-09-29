package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.dto.MovieDTO;
import com.texoit.worstmovie.entity.dto.YearsDTO;
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
    public ResponseEntity<List<MovieDTO>> findAllByYear(@RequestParam Integer year, @RequestParam(required = false) Boolean winner) {
        List<MovieDTO> found;
        if(Objects.isNull(winner)) {
            found = this.movieService.findAllByYear(year);
        } else {
            found = this.movieService.findAllByYearAndWinner(year, winner);
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/year/winner-count")
    public ResponseEntity<YearsDTO> findAllYearsByWinnerHigherOne(@RequestParam Integer minCount) {
        return ResponseEntity.ok(this.movieService.findAllYearsByWinnerHigherOne(minCount));
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(this.movieService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            this.movieService.delete(id);
            return ResponseEntity.ok().build();
        } catch (MovieIsWinnerException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/import/csv")
    public ResponseEntity<?> importFromCsv(@RequestPart(name = "csv", required = false) MultipartFile[] csv) {
        try {
            this.movieService.importFromCsv(csv);
            return ResponseEntity.created(new URI("http://localhost:8080/api/movie")).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
