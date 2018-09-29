package com.texoit.worstmovie.entity.dto;

import java.io.Serializable;

public class ProducerMinMaxAwardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProducerIntervalAwardDTO min;
    private ProducerIntervalAwardDTO max;

    public ProducerIntervalAwardDTO getMin() {
        return min;
    }

    public void setMin(ProducerIntervalAwardDTO min) {
        this.min = min;
    }

    public ProducerIntervalAwardDTO getMax() {
        return max;
    }

    public void setMax(ProducerIntervalAwardDTO max) {
        this.max = max;
    }
}
