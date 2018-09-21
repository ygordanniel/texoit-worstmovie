package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.repository.StudioRepository;
import com.texoit.worstmovie.service.StudioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class StudioServiceImpl extends BaseServiceImpl<StudioRepository, Studio> implements StudioService {

    @Override
    public Studio findByName(String name) {
        return this.repository.findByName(name);
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
}
