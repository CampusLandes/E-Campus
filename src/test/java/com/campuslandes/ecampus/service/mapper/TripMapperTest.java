package com.campuslandes.ecampus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TripMapperTest {

    private TripMapper tripMapper;

    @BeforeEach
    public void setUp() {
        tripMapper = new TripMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tripMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tripMapper.fromId(null)).isNull();
    }
}
