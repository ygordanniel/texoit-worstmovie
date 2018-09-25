package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.dto.YearsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface YearsMapper {

    default YearsDTO objListToYearsDTO(List<Object[]> obj) {
        YearWinnerMapper mapper = Mappers.getMapper(YearWinnerMapper.class);
        YearsDTO dto = new YearsDTO();
        obj.forEach(ob -> dto.add(mapper.objToYearWinnerDTO(ob)));
        return dto;
    }
}
