package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieResource {

    private MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/by-year")
    public ResponseEntity<List<Movie>> findAllByYear(@RequestParam("year") Integer year) {
        return ResponseEntity.ok(this.movieService.findAllByYear(year));
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(this.movieService.findAll());
    }

    @PutMapping("/import/csv")
    public ResponseEntity<Integer> importFromCsv(@RequestPart(name = "csv", required = false) MultipartFile[] csv) {
        return ResponseEntity.ok(this.movieService.importFromCsv(csv));
    }
}
