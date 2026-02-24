package com.example.StreamBase.models.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;
}
