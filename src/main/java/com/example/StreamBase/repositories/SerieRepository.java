package com.example.StreamBase.repositories;

import com.example.StreamBase.models.entities.Serie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    List<Serie> findByTitle(String title);

    List<Serie> findByPlayCountGreaterThan(Integer playCount);

    @Query("""
        SELECT sg.serie
        FROM SerieGenre sg
        WHERE LOWER(sg.genre.name) = LOWER(:genreName)
    """)
    List<Serie> findSeriesByGenreName(@Param("genreName") String genreName);

    @Query("""
        SELECT DISTINCT sg.serie
        FROM SerieGenre sg
        WHERE sg.genre.id = :genreId
    """)
    Page<Serie> findByGenreId(@Param("genreId") Long genreId, Pageable pageable);

}