package com.texoit.worstmovie.service;

import com.texoit.worstmovie.entity.Studio;

public interface StudioService extends BaseService<Studio> {

    Studio findByName(String name);
    Studio findOrCreate(String name);
}
