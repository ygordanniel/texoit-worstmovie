package com.texoit.worstmovie.service;

import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.StudioDTO;
import com.texoit.worstmovie.entity.dto.StudiosWinCountDTO;

public interface StudioService extends BaseService<Studio> {

    StudioDTO findByName(String name);
    Studio findOrCreate(String name);
    StudiosWinCountDTO findStudioAndWinCount();
}
