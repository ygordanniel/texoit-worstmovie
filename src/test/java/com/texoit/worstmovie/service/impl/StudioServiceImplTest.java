package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Studio;
import com.texoit.worstmovie.entity.dto.StudioDTO;
import com.texoit.worstmovie.entity.dto.StudiosWinCountDTO;
import com.texoit.worstmovie.entity.mapper.StudioMapper;
import com.texoit.worstmovie.entity.mapper.StudiosWinCountMapper;
import com.texoit.worstmovie.repository.StudioRepository;
import com.texoit.worstmovie.util.EntityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudioServiceImplTest {

    public static final String SOME_STUDIO_NAME = "Some Studio";

    @Mock
    private StudioRepository repository;
    @Mock
    private StudioMapper mapper;
    @Mock
    private StudiosWinCountMapper studiosWinCountMapper;
    @InjectMocks
    private StudioServiceImpl studioService;

    @Before
    public void init() {
        when(repository.findByName(SOME_STUDIO_NAME)).thenReturn(EntityUtil.getStudio());
        when(mapper.studioToStudioDTO(any(Studio.class))).thenReturn(EntityUtil.getStudioDTO());
    }

    @Test
    public void searchStudioByNameFindOne() {
        StudioDTO found = studioService.findByName(SOME_STUDIO_NAME);
        assertEquals(EntityUtil.getStudioDTO().getName(), found.getName());
    }

    @Test
    public void searchStudioByNameFindNone() {
        StudioDTO found = studioService.findByName("Some Other Studio");
        assertNull(found);
    }

    @Test
    public void findOrCreateFindOne() {
        Studio found = studioService.findOrCreate(SOME_STUDIO_NAME);
        assertEquals(EntityUtil.getStudio().getId(), found.getId());
    }

    @Test
    public void findOrCreateCreateOne() {
        Studio other = EntityUtil.getStudio();
        other.setName("Some Other Studio");
        other.setId(2L);
        when(repository.save(any(Studio.class))).thenReturn(other);
        Studio created = studioService.findOrCreate("Some Other Studio");
        assertEquals(other.getId(), created.getId());
    }

    @Test
    public void searchStudioAndWinCount() {
        when(repository.findStudioAndWinCount()).thenReturn(Collections.singletonList(new Object[]{SOME_STUDIO_NAME, 3}));
        when(studiosWinCountMapper.objListToStudiosWinCountDTO(anyList())).thenReturn(EntityUtil.getStudiosWinCountDTO());
        StudiosWinCountDTO studioAndWinCount = studioService.findStudioAndWinCount();
        assertTrue(studioAndWinCount.getStudios().size() == 1);

        assertEquals(SOME_STUDIO_NAME, studioAndWinCount.getStudios().get(0).getName());
        assertEquals(new Integer(3), studioAndWinCount.getStudios().get(0).getWinCount());
    }
}