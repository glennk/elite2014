package com.grk.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.grk.Application;
import com.grk.config.MongoConfig;
import com.grk.core.domain.Player;
import com.grk.core.domain.Team;
import com.grk.core.domain.fixture.PlayersFixture;
import com.grk.core.repository.PlayerRepository;
import com.grk.core.repository.TeamRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class, MongoConfig.class })
public class PlayerRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private PlayerRepository prepo;

	@Autowired
	private Environment env;

	@Test
	public void savesPlayerCorrectly() {
		Player p = PlayersFixture.standardPlayer();
		Player result = prepo.save(p);
		assertEquals(result.getId(), p.getId());
		assertNull(result.getTeam_id());
	}

	@Test
	public void savesPlayerWithTeamIdCorrectly() {
		Player p = PlayersFixture.standardPlayerWithTeam();
		Player result = prepo.save(p);
		assertEquals(result.getId(), p.getId());
		assertEquals(result.getTeam_id(), p.getTeam_id());
	}

	@Test
	public void queryPlayerByLastname() {
		List<Player> lp = prepo.findByLastname("Kronschnabl");
		assertTrue(lp.size() > 0);
	}
}
