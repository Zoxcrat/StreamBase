package com.example.StreamBase.controllers;

import com.example.StreamBase.models.dto.input.SerieCreateDTO;
import com.example.StreamBase.models.dto.output.SerieOutputDTO;
import com.example.StreamBase.services.SerieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SerieController {

    private final SerieService serieService;

    @PostMapping
    public ResponseEntity<SerieOutputDTO> create(@Valid @RequestBody SerieCreateDTO dto) {
        SerieOutputDTO serie = serieService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(serie);
    }

    @GetMapping
    public ResponseEntity<Page<SerieOutputDTO>> getAll(Pageable pageable) {
        Page<SerieOutputDTO> series = serieService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieOutputDTO> getById(@PathVariable Long id) {
        SerieOutputDTO serie = serieService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(serie);
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<Page<SerieOutputDTO>> getByGenre(
            @PathVariable Long genreId,
            Pageable pageable) {
        Page<SerieOutputDTO> series = serieService.getByGenre(genreId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(series);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
