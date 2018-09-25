package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.dto.StudioWinCountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioWinCountMapper {

    default StudioWinCountDTO objToStudioWinCountDTO(Object[] obj) {
        return new StudioWinCountDTO(obj[0].toString(), Integer.valueOf(obj[1].toString()));
    }
}
