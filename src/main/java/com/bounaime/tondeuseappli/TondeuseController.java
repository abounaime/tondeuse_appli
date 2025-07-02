package com.bounaime.tondeuseappli;

import com.bounaime.tondeuseappli.dto.TondeuseRequest;
import com.bounaime.tondeuseappli.dto.TondeuseResponse;
import com.bounaime.tondeuseappli.service.TondeuseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tondeuses")
public class TondeuseController {
    private final TondeuseService tondeuseService;

    public TondeuseController(TondeuseService tondeuseService) {
        this.tondeuseService = tondeuseService;
    }

    @PostMapping
    public ResponseEntity<List<TondeuseResponse>> start(@RequestBody TondeuseRequest request) {
        return ResponseEntity.ok().body(tondeuseService.start(request));
    }
}
