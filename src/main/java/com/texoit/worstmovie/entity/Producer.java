package com.texoit.worstmovie.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "SEQ_PRODUCER")
public class Producer extends Domain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="MOVIE_PRODUCER", joinColumns=
        {@JoinColumn(name="SEQ_DOMAIN")}, inverseJoinColumns=
        {@JoinColumn(name="SEQ_MOVIE")})
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
