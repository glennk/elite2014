package com.grk.config;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.grk.Application;

/**
 * This test ensures that once everything is wired together, the wiring in the
 * WebConfig is correct and the appropriate controllers are in attendance.
 * 
 * The test validates the WebConfig by mocking requests which exercise the
 * handler mappings. The full responses are also confirmed to be correct. More
 * testing could be done, but you've already asserted that your controllers
 * should work appropriately in the previous steps. This test is simply there to
 * show you that now you are configuring those components using Spring
 * JavaConfig properly.
 * 
 * @author grk
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Application.class })
//		MongoConfig.class, ServiceConfig.class })
public class WebDomainIntegrationTest extends AbstractIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.build();
//	}

	@Test
	public void getHome() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		mockMvc.perform(get("/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().size(2))
				.andExpect(model().attribute("playerList", hasSize(1)))
				.andExpect(content().string(containsString("lastname-via-abstract")));
	}
}
