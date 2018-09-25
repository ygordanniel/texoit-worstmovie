package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.StudioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    StudioDTO studioToStudioDTO(Studio studio);
}
