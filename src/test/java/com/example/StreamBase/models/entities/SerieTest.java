package com.example.StreamBase.models.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SerieTest {

    @Test
    void shouldCalculateTotalWatchHours() {
        
        Serie serie = new Serie();
        serie.setTotalDurationMinutes(120); 
        serie.setPlayCount(5);

        
        double totalHours = serie.totalWatchHours();

        
        assertThat(totalHours).isEqualTo(10.0); 
    }

    @Test
    void shouldReturnTrueWhenSerieIsPopular() {
        
        Serie serie = new Serie();
        serie.setPlayCount(1500);

        
        boolean isPopular = serie.isPopular();

        
        assertThat(isPopular).isTrue();
    }

    @Test
    void shouldReturnFalseWhenSerieIsNotPopular() {
        
        Serie serie = new Serie();
        serie.setPlayCount(500);

        
        boolean isPopular = serie.isPopular();

        
        assertThat(isPopular).isFalse();
    }

    @Test
    void shouldIncrementPlayCount() {
        
        Serie serie = new Serie();
        serie.setPlayCount(10);

        
        serie.incrementPlayCount(5);

        
        assertThat(serie.getPlayCount()).isEqualTo(15);
    }
}
