package com.example.StreamBase.controllers;

import com.example.StreamBase.models.dto.input.GenreCreateDTO;
import com.example.StreamBase.models.dto.output.GenreOutputDTO;
import com.example.StreamBase.services.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreOutputDTO> create(@Valid @RequestBody GenreCreateDTO dto) {
        GenreOutputDTO genre = genreService.create(dto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @GetMapping
    public ResponseEntity<Page<GenreOutputDTO>> getAll(Pageable pageable) {
        Page<GenreOutputDTO> genres = genreService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreOutputDTO> getById(@PathVariable Long id) {
        GenreOutputDTO genre = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(genre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
