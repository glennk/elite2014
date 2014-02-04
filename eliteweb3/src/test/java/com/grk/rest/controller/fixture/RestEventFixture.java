package com.grk.rest.controller.fixture;

import static com.grk.rest.controller.fixture.RestDataFixture.*;

import com.grk.core.event.AllPlayersEvent;
import com.grk.core.event.AllTeamsEvent;
import com.grk.core.event.PlayerCreatedEvent;
import com.grk.core.event.PlayerDetailsEvent;
import com.grk.core.event.TeamCreatedEvent;
import com.grk.core.event.TeamDetailsEvent;

public class RestEventFixture {

	public static AllTeamsEvent allTeamsEvent() {
		return new AllTeamsEvent(allTeams());
	}

	public static TeamCreatedEvent teamCreated(String id) {
		TeamCreatedEvent evt = new TeamCreatedEvent(id, customTeam(id));

		return evt;
	}

	public static TeamDetailsEvent teamDetailsEvent(String id) {
		return new TeamDetailsEvent(id, customTeam(id));
	}

	public static TeamDetailsEvent teamDetailsNotFound(String id) {
		return TeamDetailsEvent.notFound(id);
	}

	public static AllPlayersEvent allPlayersEvent() {
		return new AllPlayersEvent(allPlayers());
	}

	public static PlayerCreatedEvent playerCreated(String id) {
		PlayerCreatedEvent evt = new PlayerCreatedEvent(id, customPlayer(id));

		return evt;
	}

	public static PlayerDetailsEvent playerDetailsEvent(String id) {
		return new PlayerDetailsEvent(id, customPlayer(id));
	}

	public static PlayerDetailsEvent playerDetailsNotFound(String id) {
		return PlayerDetailsEvent.notFound(id);
	}

}
