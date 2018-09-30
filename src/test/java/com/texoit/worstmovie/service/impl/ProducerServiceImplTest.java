package com.texoit.worstmovie.service.impl;

import com.texoit.worstmovie.entity.Producer;
import com.texoit.worstmovie.entity.dto.ProducerDTO;
import com.texoit.worstmovie.entity.dto.ProducerMinMaxAwardDTO;
import com.texoit.worstmovie.entity.mapper.ProducerMapper;
import com.texoit.worstmovie.repository.ProducerRepository;
import com.texoit.worstmovie.util.EntityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProducerServiceImplTest {
    
    public static final String SOME_PRODUCER_NAME = "Some Producer";

    @Mock
    private ProducerRepository repository;
    @Mock
    private ProducerMapper mapper;
    @InjectMocks
    private ProducerServiceImpl producerService;

    @Before
    public void init() {
        when(repository.findByName(SOME_PRODUCER_NAME)).thenReturn(EntityUtil.getProducer());
        when(mapper.producerToProducerDTO(any(Producer.class))).thenReturn(EntityUtil.getProducerDTO());
    }

    @Test
    public void searchProducerByNameFindOne() {
        ProducerDTO someProducer = producerService.findByName(SOME_PRODUCER_NAME);
        assertEquals(EntityUtil.getProducerDTO().getName(), someProducer.getName());
    }

    @Test
    public void searchProducerByNameFindNone() {
        ProducerDTO someProducer = producerService.findByName("Some Other Producer");
        assertNull(someProducer);
    }

    @Test
    public void findOrCreateFindOne() {
        Producer foundProducer = producerService.findOrCreate(SOME_PRODUCER_NAME);
        assertEquals(EntityUtil.getProducer().getId(), foundProducer.getId());
    }

    @Test
    public void findOrCreateCreateOne() {
        Producer otherProducer = EntityUtil.getProducer();
        otherProducer.setName("Some Other Producer");
        otherProducer.setId(2L);
        when(repository.save(any(Producer.class))).thenReturn(otherProducer);
        Producer createProducer = producerService.findOrCreate("Some Other Producer");
        assertEquals(otherProducer.getId(), createProducer.getId());
    }

    @Test
    public void searchAllProducersByMovieWinnerFindOne() {
        when(repository.findAllByMovieWinner()).thenReturn(Collections.singletonList(EntityUtil.getProducer()));
        List<Producer> allByMovieWinner = producerService.findAllByMovieWinner();
        assertTrue(allByMovieWinner.size() == 1);
        assertEquals(EntityUtil.getProducer().getId(), allByMovieWinner.get(0).getId());
    }

    @Test
    public void searchProducerMinMaxAward() {
        when(repository.findAllByMovieWinner()).thenReturn(Arrays.asList(EntityUtil.getProducerMin(),
                EntityUtil.getProducerBetweenMedium(),
                EntityUtil.getProducerMax(),
                EntityUtil.getProducerBetweenLong()));
        ProducerMinMaxAwardDTO minMaxAward = producerService.findMinMaxAward();

        assertTrue(minMaxAward.getMin().getInterval() == 1);
        assertTrue(minMaxAward.getMin().getPreviousWin() == 2017);
        assertTrue(minMaxAward.getMin().getFollowingWin() == 2018);

        assertTrue(minMaxAward.getMax().getInterval() == 26);
        assertTrue(minMaxAward.getMax().getPreviousWin() == 1991);
        assertTrue(minMaxAward.getMax().getFollowingWin() == 2017);
    }
}