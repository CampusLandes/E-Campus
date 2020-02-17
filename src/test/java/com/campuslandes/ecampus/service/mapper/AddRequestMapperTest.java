package com.campuslandes.ecampus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AddRequestMapperTest {

    private AddRequestMapper addRequestMapper;

    @BeforeEach
    public void setUp() {
        addRequestMapper = new AddRequestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(addRequestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(addRequestMapper.fromId(null)).isNull();
    }
}
