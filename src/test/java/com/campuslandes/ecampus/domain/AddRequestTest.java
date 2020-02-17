package com.campuslandes.ecampus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.campuslandes.ecampus.web.rest.TestUtil;

public class AddRequestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddRequest.class);
        AddRequest addRequest1 = new AddRequest();
        addRequest1.setId(1L);
        AddRequest addRequest2 = new AddRequest();
        addRequest2.setId(addRequest1.getId());
        assertThat(addRequest1).isEqualTo(addRequest2);
        addRequest2.setId(2L);
        assertThat(addRequest1).isNotEqualTo(addRequest2);
        addRequest1.setId(null);
        assertThat(addRequest1).isNotEqualTo(addRequest2);
    }
}
