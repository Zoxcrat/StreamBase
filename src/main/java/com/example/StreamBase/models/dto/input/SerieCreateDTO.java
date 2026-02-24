package com.example.StreamBase.models.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SerieCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Review is required")
    private String review;

    @NotNull(message = "Total duration is required")
    @Min(value = 1, message = "Duration must be greater than 0")
    private Integer totalDurationInMinutes;

    @NotNull(message = "Genre id is required")
    private Long genreId;
}