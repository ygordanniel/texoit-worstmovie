package com.texoit.worstmovie.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "SEQ_STUDIO")
public class Studio extends Domain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="MOVIE_STUDIO", joinColumns=
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
