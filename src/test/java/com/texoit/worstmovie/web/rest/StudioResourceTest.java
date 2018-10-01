package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.dto.ResponseDTO;
import com.texoit.worstmovie.entity.dto.StudiosWinCountDTO;
import com.texoit.worstmovie.service.StudioService;
import com.texoit.worstmovie.service.impl.StudioServiceImplTest;
import com.texoit.worstmovie.util.EntityUtil;
import com.texoit.worstmovie.util.ResponseEntityTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudioResourceTest {

    @Mock
    private StudioService service;
    @InjectMocks
    private StudioResource resource;

    @Test
    public void findStudioAndWinCount() {
        when(service.findStudioAndWinCount()).thenReturn(EntityUtil.getStudiosWinCountDTO());
        ResponseEntity<ResponseDTO<StudiosWinCountDTO>> response = resource.findStudioAndWinCount();
        ResponseEntityTestUtil.statusOkBodyNotNull(response);
        assertTrue(response.getBody().getContent().getStudios().size() == 1);
        assertEquals(StudioServiceImplTest.SOME_STUDIO_NAME, response.getBody().getContent().getStudios().get(0).getName());
        assertEquals(new Integer(3), response.getBody().getContent().getStudios().get(0).getWinCount());
    }
}