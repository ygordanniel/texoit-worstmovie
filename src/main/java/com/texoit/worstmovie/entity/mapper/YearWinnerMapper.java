package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.dto.YearWinnerCountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface YearWinnerMapper {

    default YearWinnerCountDTO objToYearWinnerDTO(Object[] obj) {
        return new YearWinnerCountDTO(Integer.valueOf(obj[0].toString()), Integer.valueOf(obj[1].toString()));
    }
}
