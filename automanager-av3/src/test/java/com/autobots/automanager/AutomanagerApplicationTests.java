package com.autobots.automanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AutomanagerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testEmpresaEndpoint() throws Exception {
		mockMvc.perform(get("/empresa"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUsuarioEndpoint() throws Exception {
		mockMvc.perform(get("/usuario"))
				.andExpect(status().isOk());
	}

	@Test
	public void testVeiculoEndpoint() throws Exception {
		mockMvc.perform(get("/veiculo"))
				.andExpect(status().isOk());
	}

	@Test
	public void testMercadoriaEndpoint() throws Exception {
		mockMvc.perform(get("/mercadoria"))
				.andExpect(status().isOk());
	}

	@Test
	public void testServicoEndpoint() throws Exception {
		mockMvc.perform(get("/servico"))
				.andExpect(status().isOk());
	}

	@Test
	public void testVendaEndpoint() throws Exception {
		mockMvc.perform(get("/venda"))
				.andExpect(status().isOk());
	}
}
