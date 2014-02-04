package com.grk.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.grk.Application;
import com.grk.config.MongoConfig;
import com.grk.core.domain.Team;
import com.grk.core.domain.fixture.TeamsFixture;
import com.grk.core.repository.TeamRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class, MongoConfig.class })
public class TeamRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private TeamRepository trepo;

	@Autowired
	private Environment env;
	
	@Test
	public void savesTeamCorrectly()
	{
		Team t = TeamsFixture.standardTeam();
		Team result = trepo.save(t);
		assertEquals(result.getId(), t.getId());
	}

	@Test
	public void queryTeamByName()
	{
		List<Team> lt = trepo.findByName("Austin Elite 15U");
		assertTrue(lt.size() > 0);
	}
}
