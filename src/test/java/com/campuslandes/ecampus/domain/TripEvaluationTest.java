package com.campuslandes.ecampus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class TripEvaluationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TripEvaluation.class);
        TripEvaluation tripEvaluation1 = new TripEvaluation();
        tripEvaluation1.setId(1L);
        TripEvaluation tripEvaluation2 = new TripEvaluation();
        tripEvaluation2.setId(tripEvaluation1.getId());
        assertThat(tripEvaluation1).isEqualTo(tripEvaluation2);
        tripEvaluation2.setId(2L);
        assertThat(tripEvaluation1).isNotEqualTo(tripEvaluation2);
        tripEvaluation1.setId(null);
        assertThat(tripEvaluation1).isNotEqualTo(tripEvaluation2);
    }
}
