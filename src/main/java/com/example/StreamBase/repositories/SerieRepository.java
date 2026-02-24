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

    
    Page<Serie> findBySerieGenresGenreId(Long genreId, Pageable pageable);

    
    @Query("SELECT s FROM Serie s WHERE s.playCount > :minPlayCount")
    List<Serie> findPopularSeries(@Param("minPlayCount") Integer minPlayCount);

}