package com.campuslandes.ecampus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EventTypeMapperTest {

    private EventTypeMapper eventTypeMapper;

    @BeforeEach
    public void setUp() {
        eventTypeMapper = new EventTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(eventTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventTypeMapper.fromId(null)).isNull();
    }
}
