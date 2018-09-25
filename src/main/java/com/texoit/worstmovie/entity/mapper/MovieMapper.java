package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.Movie;
import com.texoit.worstmovie.entity.dto.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudioMapper.class, ProducerMapper.class})
public interface MovieMapper {

    MovieDTO movieToMovieDTO(Movie movie);
}
