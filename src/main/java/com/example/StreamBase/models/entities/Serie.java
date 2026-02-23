package com.example.StreamBase.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String review;

    private Integer totalDurationMinutes;

    private Integer playCount = 0;

    private String posterUrl;


    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Season> seasons = new ArrayList<>();


    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SerieGenre> serieGenres = new ArrayList<>();



    public void incrementPlayCount(int plays) {
        this.playCount += plays;
    }

    public double totalWatchHours() {
        return (this.totalDurationMinutes / 60.0) * this.playCount;
    }

    public boolean isPopular() {
        return this.playCount != null && this.playCount > 1000;
    }

    public void addSeason(Season season) {
        seasons.add(season);
        season.setSerie(this);
    }

    public void addGenre(Genre genre) {
        SerieGenre relation = new SerieGenre(this, genre, LocalDate.now());
        serieGenres.add(relation);
        genre.getSerieGenres().add(relation);
    }
}