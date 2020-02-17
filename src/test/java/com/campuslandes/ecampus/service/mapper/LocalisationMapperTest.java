package com.campuslandes.ecampus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LocalisationMapperTest {

    private LocalisationMapper localisationMapper;

    @BeforeEach
    public void setUp() {
        localisationMapper = new LocalisationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(localisationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(localisationMapper.fromId(null)).isNull();
    }
}
