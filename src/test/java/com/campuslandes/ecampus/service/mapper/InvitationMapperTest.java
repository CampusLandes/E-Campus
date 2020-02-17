package com.campuslandes.ecampus.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InvitationMapperTest {

    private InvitationMapper invitationMapper;

    @BeforeEach
    public void setUp() {
        invitationMapper = new InvitationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(invitationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invitationMapper.fromId(null)).isNull();
    }
}
