package com.example.StreamBase.services.impl;

import com.example.StreamBase.exceptions.ResourceNotFoundException;
import com.example.StreamBase.models.dto.input.SerieCreateDTO;
import com.example.StreamBase.models.dto.output.GenreOutputDTO;
import com.example.StreamBase.models.dto.output.SerieOutputDTO;
import com.example.StreamBase.models.entities.Serie;
import com.example.StreamBase.models.entities.SerieGenre;
import com.example.StreamBase.repositories.GenreRepository;
import com.example.StreamBase.repositories.SerieGenreRepository;
import com.example.StreamBase.repositories.SerieRepository;
import com.example.StreamBase.services.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SerieServiceImpl implements SerieService {

    private final SerieRepository serieRepository;
    private final GenreRepository genreRepository;
    private final SerieGenreRepository serieGenreRepository;

    @Override
    public SerieOutputDTO create(SerieCreateDTO dto) {

        Serie serie = new Serie();
        serie.setTitle(dto.getName());
        serie.setReview(dto.getReview());
        serie.setTotalDurationMinutes(dto.getTotalDurationInMinutes());
        serie.setPlayCount(0);

        Serie savedSerie = serieRepository.save(serie);

        
        dto.getGenreIds().forEach(genreId -> {
            var genre = genreRepository.findById(genreId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Genre not found: " + genreId));

            SerieGenre serieGenre = new SerieGenre();
            serieGenre.setSerie(savedSerie);
            serieGenre.setGenre(genre);
            serieGenre.setAssociatedAt(LocalDate.now());

            serieGenreRepository.save(serieGenre);
            savedSerie.getSerieGenres().add(serieGenre);
        });

        return mapToDTO(savedSerie);
    }


    @Override
    public SerieOutputDTO getById(Long id) {

        Serie serie = serieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Serie not found"));

        return mapToDTO(serie);
    }


    @Override
    public Page<SerieOutputDTO> getAll(Pageable pageable) {

        return serieRepository.findAll(pageable)
                .map(this::mapToDTO);
    }


    @Override
    public Page<SerieOutputDTO> getByGenre(Long genreId, Pageable pageable) {

        if (!genreRepository.existsById(genreId)) {
            throw new ResourceNotFoundException("Genre not found");
        }

        return serieRepository
                .findBySerieGenresGenreId(genreId, pageable)
                .map(this::mapToDTO);
    }


    @Override
    public void delete(Long id) {

        Serie serie = serieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Serie not found"));

        serieRepository.delete(serie);
    }


    private SerieOutputDTO mapToDTO(Serie serie) {

        SerieOutputDTO dto = new SerieOutputDTO();

        dto.setId(serie.getId());
        dto.setName(serie.getTitle());
        dto.setReview(serie.getReview());
        dto.setTotalDurationInMinutes(serie.getTotalDurationMinutes());
        dto.setTotalViews(serie.getPlayCount());
        dto.setTotalWatchHours(serie.totalWatchHours());
        dto.setPopular(serie.isPopular());

       
        List<GenreOutputDTO> genres = serie.getSerieGenres()
                .stream()
                .map(SerieGenre::getGenre)
                .map(genre -> {
                    GenreOutputDTO genreDTO = new GenreOutputDTO();
                    genreDTO.setId(genre.getId());
                    genreDTO.setName(genre.getName());
                    return genreDTO;
                })
                .collect(Collectors.toList());

        dto.setGenres(genres);

        return dto;
    }
}
