package com.campuslandes.ecampus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TripEvaluationMapperTest {

    private TripEvaluationMapper tripEvaluationMapper;

    @BeforeEach
    public void setUp() {
        tripEvaluationMapper = new TripEvaluationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tripEvaluationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tripEvaluationMapper.fromId(null)).isNull();
    }
}
