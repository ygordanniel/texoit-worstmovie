package com.texoit.worstmovie.entity.mapper;

import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.dto.ProducerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    ProducerDTO producerToProducerDTO(Producer producer);
}
