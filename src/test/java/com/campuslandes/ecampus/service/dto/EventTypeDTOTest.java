package com.campuslandes.ecampus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class EventTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventTypeDTO.class);
        EventTypeDTO eventTypeDTO1 = new EventTypeDTO();
        eventTypeDTO1.setId(1L);
        EventTypeDTO eventTypeDTO2 = new EventTypeDTO();
        assertThat(eventTypeDTO1).isNotEqualTo(eventTypeDTO2);
        eventTypeDTO2.setId(eventTypeDTO1.getId());
        assertThat(eventTypeDTO1).isEqualTo(eventTypeDTO2);
        eventTypeDTO2.setId(2L);
        assertThat(eventTypeDTO1).isNotEqualTo(eventTypeDTO2);
        eventTypeDTO1.setId(null);
        assertThat(eventTypeDTO1).isNotEqualTo(eventTypeDTO2);
    }
}
