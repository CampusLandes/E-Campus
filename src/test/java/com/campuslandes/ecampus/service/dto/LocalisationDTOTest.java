package com.campuslandes.ecampus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class LocalisationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalisationDTO.class);
        LocalisationDTO localisationDTO1 = new LocalisationDTO();
        localisationDTO1.setId(1L);
        LocalisationDTO localisationDTO2 = new LocalisationDTO();
        assertThat(localisationDTO1).isNotEqualTo(localisationDTO2);
        localisationDTO2.setId(localisationDTO1.getId());
        assertThat(localisationDTO1).isEqualTo(localisationDTO2);
        localisationDTO2.setId(2L);
        assertThat(localisationDTO1).isNotEqualTo(localisationDTO2);
        localisationDTO1.setId(null);
        assertThat(localisationDTO1).isNotEqualTo(localisationDTO2);
    }
}
