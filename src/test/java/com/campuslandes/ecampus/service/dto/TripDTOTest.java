package com.campuslandes.ecampus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class TripDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TripDTO.class);
        TripDTO tripDTO1 = new TripDTO();
        tripDTO1.setId(1L);
        TripDTO tripDTO2 = new TripDTO();
        assertThat(tripDTO1).isNotEqualTo(tripDTO2);
        tripDTO2.setId(tripDTO1.getId());
        assertThat(tripDTO1).isEqualTo(tripDTO2);
        tripDTO2.setId(2L);
        assertThat(tripDTO1).isNotEqualTo(tripDTO2);
        tripDTO1.setId(null);
        assertThat(tripDTO1).isNotEqualTo(tripDTO2);
    }
}
