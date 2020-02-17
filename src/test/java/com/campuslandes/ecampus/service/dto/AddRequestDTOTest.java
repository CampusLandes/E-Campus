package com.campuslandes.ecampus.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class AddRequestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddRequestDTO.class);
        AddRequestDTO addRequestDTO1 = new AddRequestDTO();
        addRequestDTO1.setId(1L);
        AddRequestDTO addRequestDTO2 = new AddRequestDTO();
        assertThat(addRequestDTO1).isNotEqualTo(addRequestDTO2);
        addRequestDTO2.setId(addRequestDTO1.getId());
        assertThat(addRequestDTO1).isEqualTo(addRequestDTO2);
        addRequestDTO2.setId(2L);
        assertThat(addRequestDTO1).isNotEqualTo(addRequestDTO2);
        addRequestDTO1.setId(null);
        assertThat(addRequestDTO1).isNotEqualTo(addRequestDTO2);
    }
}
