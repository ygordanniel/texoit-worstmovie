package com.texoit.worstmovie.web.rest;

import com.texoit.worstmovie.entity.dto.StudiosWinCountDTO;
import com.texoit.worstmovie.service.StudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studio")
public class StudioResource {

    private StudioService studioService;

    public StudioResource(StudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping("/win-count")
    public ResponseEntity<StudiosWinCountDTO> findStudioAndWinCount() {
        return ResponseEntity.ok(studioService.findStudioAndWinCount());
    }
}
