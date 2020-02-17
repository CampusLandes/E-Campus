package com.campuslandes.ecampus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class TripEvaluationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TripEvaluationDTO.class);
        TripEvaluationDTO tripEvaluationDTO1 = new TripEvaluationDTO();
        tripEvaluationDTO1.setId(1L);
        TripEvaluationDTO tripEvaluationDTO2 = new TripEvaluationDTO();
        assertThat(tripEvaluationDTO1).isNotEqualTo(tripEvaluationDTO2);
        tripEvaluationDTO2.setId(tripEvaluationDTO1.getId());
        assertThat(tripEvaluationDTO1).isEqualTo(tripEvaluationDTO2);
        tripEvaluationDTO2.setId(2L);
        assertThat(tripEvaluationDTO1).isNotEqualTo(tripEvaluationDTO2);
        tripEvaluationDTO1.setId(null);
        assertThat(tripEvaluationDTO1).isNotEqualTo(tripEvaluationDTO2);
    }
}
