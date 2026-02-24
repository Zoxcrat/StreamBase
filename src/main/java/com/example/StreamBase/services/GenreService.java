package com.example.StreamBase.services;

import com.example.StreamBase.models.dto.output.GenreOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    GenreOutputDTO create(String name);

    Page<GenreOutputDTO> getAll(Pageable pageable);

    GenreOutputDTO getById(Long id);

    void delete(Long id);
}
