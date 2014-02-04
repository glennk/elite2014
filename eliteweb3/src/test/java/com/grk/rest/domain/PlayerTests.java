package com.grk.rest.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.grk.Application;
import com.grk.config.MongoConfig;
import com.grk.core.event.PlayerDetails;
import com.grk.rest.controller.fixture.RestDataFixture;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class, MongoConfig.class })
public class PlayerTests {
	
	private Logger LOG = LoggerFactory.getLogger(PlayerTests.class);

	@Test
	public void thatPlayerDetailsCanConvertToPlayerSummary() {
		PlayerDetails p = RestDataFixture.standardPlayer();
		
		PlayerSummary ps = PlayerSummary.fromPlayerDetails(p);
		assertEquals(ps.getId(), p.getId());
		assertEquals(ps.getFirstname(), p.getFirstname());
		assertEquals(ps.getLastname(), p.getLastname());
		assertEquals(ps.getJerseyNum(), p.getJerseyNum());
		assertEquals(ps.getTeam_id(), p.getTeam_id());
		//TODO do more tests
	}
}
