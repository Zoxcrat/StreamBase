package com.example.StreamBase.services;

import com.example.StreamBase.exceptions.ResourceNotFoundException;
import com.example.StreamBase.models.dto.output.GenreOutputDTO;
import com.example.StreamBase.models.entities.Genre;
import com.example.StreamBase.repositories.GenreRepository;
import com.example.StreamBase.services.impl.GenreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    void shouldCreateGenre() {
        
        Genre savedGenre = new Genre();
        savedGenre.setId(1L);
        savedGenre.setName("Action");

        when(genreRepository.save(any(Genre.class))).thenReturn(savedGenre);

       
        GenreOutputDTO result = genreService.create("Action");

        
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Action");
        verify(genreRepository).save(any(Genre.class));
    }

    @Test
    void shouldThrowExceptionWhenGenreNotFound() {
        
        when(genreRepository.findById(999L)).thenReturn(Optional.empty());

       
        assertThatThrownBy(() -> genreService.getById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Genre not found");
    }
}
