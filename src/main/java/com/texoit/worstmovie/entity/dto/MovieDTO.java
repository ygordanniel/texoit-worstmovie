package com.texoit.worstmovie.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Integer year;
    private List<StudioDTO> studios = new ArrayList<>();
    private List<ProducerDTO> producers = new ArrayList<>();
    private Boolean winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<StudioDTO> getStudios() {
        return studios;
    }

    public void setStudios(List<StudioDTO> studios) {
        this.studios = studios;
    }

    public List<ProducerDTO> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerDTO> producers) {
        this.producers = producers;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
}
