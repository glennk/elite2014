package com.grk.core.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.grk.core.domain.Team;
import com.grk.core.event.AllTeamsEvent;
import com.grk.core.event.CreateTeamEvent;
import com.grk.core.event.RequestAllTeamsEvent;
import com.grk.core.event.RequestTeamDetailsEvent;
import com.grk.core.event.TeamCreatedEvent;
import com.grk.core.event.TeamDetails;
import com.grk.core.event.TeamDetailsEvent;
import com.grk.core.repository.TeamRepository;

public class TeamServiceImpl implements TeamService {

	private static Logger LOG = LoggerFactory.getLogger(TeamService.class);

//	@Autowired
	private TeamRepository teamRepo;

	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepo = teamRepository;
		LOG.info("TeamServiceImpl({});", teamRepository.getClass());
	}

	@Override
	public TeamDetailsEvent getTeam(RequestTeamDetailsEvent evt) {
		LOG.debug("getTeam({})", evt.getId());
		Team t = teamRepo.findOne(evt.getId());
		return new TeamDetailsEvent(t.getId(), t.toTeamDetails());
	}

	@Override
	public AllTeamsEvent getAllTeams(RequestAllTeamsEvent evt) {
		LOG.debug("getAllTeams()");
		List<TeamDetails> listOfTeamsDetails = new ArrayList<TeamDetails>();
		for (Team t : teamRepo.findAll()) {
			listOfTeamsDetails.add(t.toTeamDetails());

		}
		return new AllTeamsEvent(listOfTeamsDetails);
	}

	@Override
	public TeamCreatedEvent createTeam(CreateTeamEvent evt) {
		TeamDetails teamDetails = evt.getTeamDetails();
		LOG.debug("createTeam() from details: {}", teamDetails.toString());
		Team t = Team.fromTeamDetails(teamDetails);
		Team result = teamRepo.save(t);
		LOG.debug("team successfully created, id: {}", result.getId());
		
		TeamCreatedEvent teamCreatedEvent = new TeamCreatedEvent(
				result.getId(), result.toTeamDetails());
		return teamCreatedEvent;
	}

}
