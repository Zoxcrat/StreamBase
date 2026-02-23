package com.example.StreamBase.repositories;

import com.example.StreamBase.models.entities.SerieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieGenreRepository extends JpaRepository<SerieGenre, Long> {

    List<SerieGenre> findByGenreId(Long genreId);

    List<SerieGenre> findBySerieId(Long serieId);

}