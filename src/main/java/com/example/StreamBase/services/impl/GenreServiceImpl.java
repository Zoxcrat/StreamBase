package com.example.StreamBase.services.impl;

import com.example.StreamBase.exceptions.ResourceNotFoundException;
import com.example.StreamBase.models.dto.output.GenreOutputDTO;
import com.example.StreamBase.models.entities.Genre;
import com.example.StreamBase.repositories.GenreRepository;
import com.example.StreamBase.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreOutputDTO create(String name) {

        Genre genre = new Genre();
        genre.setName(name);

        Genre saved = genreRepository.save(genre);

        return mapToDTO(saved);
    }

    @Override
    public Page<GenreOutputDTO> getAll(Pageable pageable) {
        return genreRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    @Override
    public GenreOutputDTO getById(Long id) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Genre not found"));

        return mapToDTO(genre);
    }

    @Override
    public void delete(Long id) {

        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Genre not found");
        }

        genreRepository.deleteById(id);
    }

    private GenreOutputDTO mapToDTO(Genre genre) {

        GenreOutputDTO dto = new GenreOutputDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());

        return dto;
    }
}
