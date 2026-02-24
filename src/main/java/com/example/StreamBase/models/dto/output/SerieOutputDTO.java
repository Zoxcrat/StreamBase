package com.example.StreamBase.models.dto.output;

import lombok.Data;

@Data
public class SerieOutputDTO {

    private Long id;
    private String name;
    private String review;
    private Integer totalDurationInMinutes;
    private Integer totalViews;
    private Double totalWatchHours;
    private Boolean popular;
    private GenreOutputDTO genre;
}