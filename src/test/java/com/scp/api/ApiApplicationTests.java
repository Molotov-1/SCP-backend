package com.scp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void openApiDocumentaLasOperacionesDeScp() throws Exception {
		mockMvc.perform(get("/api-docs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.info.title").value("SCP API"))
				.andExpect(jsonPath("$.paths['/api/v1/scps'].get").exists())
				.andExpect(jsonPath("$.paths['/api/v1/scps'].post").exists())
				.andExpect(jsonPath("$.paths['/api/v1/scps/{id}'].get").exists())
				.andExpect(jsonPath("$.paths['/api/v1/scps/{id}'].put").exists())
				.andExpect(jsonPath("$.paths['/api/v1/scps/{id}'].delete").exists())
				.andExpect(jsonPath("$.paths['/api/v1/scps/{id}/stock'].patch").exists())
				.andExpect(jsonPath("$.components.schemas.ActualizarStockRequest.properties.cantidad").exists())
				.andExpect(jsonPath("$.components.schemas.ActualizarStockRequest.required[0]").value("cantidad"));
	}

}
