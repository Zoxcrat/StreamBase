package com.example.StreamBase.repositories;

import com.example.StreamBase.models.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findBySerieId(Long serieId);

}