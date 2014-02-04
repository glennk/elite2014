package com.grk.config;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.grk.Application;
import com.grk.core.event.AllPlayersEvent;
import com.grk.core.event.AllTeamsEvent;
import com.grk.core.event.CreatePlayerEvent;
import com.grk.core.event.CreateTeamEvent;
import com.grk.core.event.PlayerDetails;
import com.grk.core.event.RequestAllPlayersEvent;
import com.grk.core.event.RequestAllTeamsEvent;
import com.grk.core.event.TeamDetails;
import com.grk.core.services.PlayerService;
import com.grk.core.services.TeamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, MongoConfig.class,
        ServiceConfig.class})
public class CoreDomainIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Test
    public void addNewTeam() {
        CreateTeamEvent evt = new CreateTeamEvent(new TeamDetails());
        teamService.createTeam(evt);
        AllTeamsEvent result = teamService
                .getAllTeams(new RequestAllTeamsEvent());
        assertEquals(2, result.getTeamDetails().size());
    }

    @Test
    public void addNewPlayer() {
        CreatePlayerEvent evt = new CreatePlayerEvent(new PlayerDetails());
        playerService.createPlayer(evt);
        AllPlayersEvent result = playerService
                .getAllPlayers(new RequestAllPlayersEvent());
        assertEquals(2, result.getPlayerDetails().size());
    }
}
