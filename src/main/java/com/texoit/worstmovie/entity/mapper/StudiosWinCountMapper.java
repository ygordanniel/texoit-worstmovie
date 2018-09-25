package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.dto.StudiosWinCountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudiosWinCountMapper {

    default StudiosWinCountDTO objListToStudiosWinCountDTO(List<Object[]> obj) {
        StudioWinCountMapper mapper = Mappers.getMapper(StudioWinCountMapper.class);
        StudiosWinCountDTO dto = new StudiosWinCountDTO();
        obj.forEach(ob -> dto.add(mapper.objToStudioWinCountDTO(ob)));
        return dto;
    }
}
