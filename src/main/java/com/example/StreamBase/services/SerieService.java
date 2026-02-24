package com.example.StreamBase.services;

import com.example.StreamBase.models.dto.input.SerieCreateDTO;
import com.example.StreamBase.models.dto.output.SerieOutputDTO;
import com.example.StreamBase.models.entities.Serie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface SerieService {

    SerieOutputDTO create(SerieCreateDTO dto);

    SerieOutputDTO getById(Long id);

    Page<SerieOutputDTO> getAll(Pageable pageable);

    Page<SerieOutputDTO> getByGenre(Long genreId, Pageable pageable);

    void delete(Long id);
}