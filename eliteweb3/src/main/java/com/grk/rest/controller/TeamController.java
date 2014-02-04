package com.grk.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.grk.core.event.AllTeamsEvent;
import com.grk.core.event.CreateTeamEvent;
import com.grk.core.event.RequestAllTeamsEvent;
import com.grk.core.event.RequestTeamDetailsEvent;
import com.grk.core.event.TeamCreatedEvent;
import com.grk.core.event.TeamDetails;
import com.grk.core.event.TeamDetailsEvent;
import com.grk.core.services.TeamService;
import com.grk.rest.domain.TeamSummary;

@Controller
@RequestMapping("/teams")
public class TeamController {

	private static Logger LOG = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private TeamService service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TeamSummary> getAllTeams() {
		LOG.debug("GET /teams");
		LOG.debug("getAllTeams(); with service: " + service.getClass());
		AllTeamsEvent evt = service.getAllTeams(new RequestAllTeamsEvent());
		LOG.debug("# of teams: " + evt.getTeamDetails().size());
		List<TeamSummary> lts = new ArrayList<TeamSummary>();
		for (TeamDetails teamDetails : evt.getTeamDetails()) {
			lts.add(TeamSummary.fromTeamDetails(teamDetails));
		}
		
		return lts;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<TeamSummary> team(@PathVariable String id) {
		LOG.debug("GET /teams/{} with service: {}", id, service.getClass());
		TeamDetailsEvent tde = service.getTeam(new RequestTeamDetailsEvent(id));
		
		if (tde.getTeamDetails() != null) {
			TeamSummary ts = TeamSummary.fromTeamDetails(tde.getTeamDetails());
			return new ResponseEntity<TeamSummary>(ts, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<TeamSummary>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TeamSummary> createTeam(@RequestBody TeamSummary team, UriComponentsBuilder builder) {
		LOG.debug("POST /team/{json payload converted to TeamSummary: {}} with service: {}", team.toString(), service.getClass());
		TeamCreatedEvent teamCreated = service.createTeam(new CreateTeamEvent(team.toTeamDetails()));
		
		TeamSummary newTeam = TeamSummary.fromTeamDetails(teamCreated.getTeamDetails());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/teams/{id}")
				.buildAndExpand(teamCreated.getNewTeamKey())
				.toUri());
		
		return new ResponseEntity<TeamSummary>(newTeam, headers, HttpStatus.CREATED);
	}
}
