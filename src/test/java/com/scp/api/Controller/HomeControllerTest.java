package com.scp.api.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HomeControllerTest {

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new HomeController())
            .build();

    @Test
    void inicioDevuelveEstadoYEndpointDeLaApi() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("API SCP funcionando"))
                .andExpect(jsonPath("$.endpointScps").value("/api/v1/scps"));
    }
}
