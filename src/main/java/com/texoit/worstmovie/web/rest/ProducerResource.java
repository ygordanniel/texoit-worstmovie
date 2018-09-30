package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.dto.ProducerMinMaxAwardDTO;
import com.texoit.worstmovie.entity.dto.ResponseDTO;
import com.texoit.worstmovie.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
public class ProducerResource {

    private ProducerService producerService;

    public ProducerResource(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/min-max")
    public ResponseEntity<ResponseDTO<ProducerMinMaxAwardDTO>> findStudioAndWinCount() {
        return ResponseEntity.ok(new ResponseDTO<>(producerService.findMinMaxAward()));
    }
}
