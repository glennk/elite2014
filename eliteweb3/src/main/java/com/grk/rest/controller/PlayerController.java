package com.grk.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.grk.core.domain.Player;
import com.grk.core.services.PlayerService;
import com.grk.rest.domain.PlayerSummary;

@Controller
@RequestMapping("/players")
public class PlayerController {

	private static Logger LOG = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private PlayerService service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<PlayerSummary> getAllPlayers() {
		LOG.info("getAllPlayers() with service: " + service.getClass());
		List<Player> players = service.getAllPlayers();
		LOG.info("# of players: " + players.size());
		List<PlayerSummary> ps = new ArrayList<PlayerSummary>();
		for (Player player : service.getAllPlayers()) {
			ps.add(PlayerSummary.fromCorePlayer(player));
		}

		return ps;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PlayerSummary> player(@PathVariable String id) {
		LOG.info("/players/{id}, id=" + id + ", with service: "
				+ service.getClass());
		Player p = service.getPlayer(id); // repository.findOne(id);
		LOG.info("p = " + p);

		if (p != null) {
			PlayerSummary pr = PlayerSummary.fromCorePlayer(p);
			return new ResponseEntity<PlayerSummary>(pr, HttpStatus.OK);
		} else {
			return new ResponseEntity<PlayerSummary>(HttpStatus.NOT_FOUND);
		}
	}
}
