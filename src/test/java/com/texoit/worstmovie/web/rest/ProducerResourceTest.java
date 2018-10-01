package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.dto.ProducerMinMaxAwardDTO;
import com.texoit.worstmovie.entity.dto.ResponseDTO;
import com.texoit.worstmovie.service.ProducerService;
import com.texoit.worstmovie.util.EntityUtil;
import com.texoit.worstmovie.util.ResponseEntityTestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProducerResourceTest {

    @Mock
    private ProducerService service;
    @InjectMocks
    private ProducerResource resource;

    @Test
    public void findStudioAndWinCount() {
        when(service.findMinMaxAward()).thenReturn(EntityUtil.getProducerMinMaxAwardDTO());
        ResponseEntity<ResponseDTO<ProducerMinMaxAwardDTO>> response = resource.findStudioAndWinCount();
        ResponseEntityTestUtil.statusOkBodyNotNull(response);

        Assert.assertEquals("Some Producer", response.getBody().getContent().getMin().getProducer());
        Assert.assertEquals(new Long(1), response.getBody().getContent().getMin().getInterval());
        Assert.assertEquals(new Integer(2017), response.getBody().getContent().getMin().getPreviousWin());
        Assert.assertEquals(new Integer(2018), response.getBody().getContent().getMin().getFollowingWin());

        Assert.assertEquals("Some Other Producer", response.getBody().getContent().getMax().getProducer());
        Assert.assertEquals(new Long(6), response.getBody().getContent().getMax().getInterval());
        Assert.assertEquals(new Integer(2010), response.getBody().getContent().getMax().getPreviousWin());
        Assert.assertEquals(new Integer(2016), response.getBody().getContent().getMax().getFollowingWin());
    }
}