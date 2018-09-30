package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.StudioDTO;
import com.texoit.worstmovie.entity.dto.StudiosWinCountDTO;
import com.texoit.worstmovie.entity.mapper.StudioMapper;
import com.texoit.worstmovie.entity.mapper.StudiosWinCountMapper;
import com.texoit.worstmovie.repository.StudioRepository;
import com.texoit.worstmovie.service.StudioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class StudioServiceImpl extends BaseServiceImpl<StudioRepository, Studio> implements StudioService {

    private StudioMapper mapper;
    private StudiosWinCountMapper studiosWinCountMapper;

    public StudioServiceImpl(StudioMapper mapper, StudiosWinCountMapper studiosWinCountMapper, StudioRepository repository) {
        super(repository);
        this.mapper = mapper;
        this.studiosWinCountMapper = studiosWinCountMapper;
    }

    @Override
    public StudioDTO findByName(String name) {
        return mapper.studioToStudioDTO(this.repository.findByName(name));
    }

    @Override
    public Studio findOrCreate(String name) {
        Studio studio = this.repository.findByName(name);
        if(Objects.isNull(studio)) {
            studio = new Studio();
            studio.setName(name);
            studio = this.repository.save(studio);
        }
        return studio;
    }

    @Override
    public StudiosWinCountDTO findStudioAndWinCount() {
        return this.studiosWinCountMapper.objListToStudiosWinCountDTO(this.repository.findStudioAndWinCount());
    }
}
