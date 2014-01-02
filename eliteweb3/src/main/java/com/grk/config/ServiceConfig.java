package com.grk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grk.core.services.PlayerService;
import com.grk.core.services.PlayerServiceImpl;

@Configuration
public class ServiceConfig {

	private static Logger LOG = LoggerFactory.getLogger(ServiceConfig.class);
	
	@Bean
	public PlayerService createService() {
		LOG.debug("createService()");
		return new PlayerServiceImpl();
	}
}
