package com.grk.rest.controller;

import static com.grk.rest.controller.fixture.RestDataFixture.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.grk.core.services.PlayerService;

public class ViewPlayerIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	PlayerController controller;

	@Mock
	PlayerService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void getAllPlayers() throws Exception {

		when(service.getAllPlayers()).thenReturn(standardListOfPlayers());

		this.mockMvc
				.perform(get("/players").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value("100"))
				.andExpect(jsonPath("$[1].id").value("101"));
	}

	@Test
	public void getPlayer() throws Exception {

		when(service.getPlayer(any(String.class))).thenReturn(standardPlayer());

		this.mockMvc
				.perform(get("/players/100").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("100"));
	}

	@Test
	public void getPlayerNotFound() throws Exception {

		when(service.getPlayer(any(String.class))).thenReturn(
				standardPlayerNotFound());

		this.mockMvc
				.perform(get("/players/101").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());
	}
}
