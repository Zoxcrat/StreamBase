package com.example.StreamBase.repositories;

import com.example.StreamBase.models.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    // Buscar por título parcialmente
    List<Serie> findByTitleContainingIgnoreCase(String title);

    // Series populares por cantidad de reproducciones
    List<Serie> findByPlayCountGreaterThan(Integer playCount);

    // JPQL usando relación intermedia
    @Query("""
        SELECT sg.serie
        FROM SerieGenre sg
        WHERE LOWER(sg.genre.name) = LOWER(:genreName)
    """)
    List<Serie> findSeriesByGenreName(@Param("genreName") String genreName);

}