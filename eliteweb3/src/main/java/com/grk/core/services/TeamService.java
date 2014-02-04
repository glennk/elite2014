package com.grk.core.services;

import com.grk.core.event.AllTeamsEvent;
import com.grk.core.event.CreateTeamEvent;
import com.grk.core.event.RequestAllTeamsEvent;
import com.grk.core.event.RequestTeamDetailsEvent;
import com.grk.core.event.TeamCreatedEvent;
import com.grk.core.event.TeamDetailsEvent;

public interface TeamService {
	
	public TeamDetailsEvent getTeam(RequestTeamDetailsEvent evt);
	
	public AllTeamsEvent getAllTeams(RequestAllTeamsEvent evt);
	
	public TeamCreatedEvent createTeam(CreateTeamEvent evt);

}
