package com.grk.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.grk.core.domain.Player;
import com.grk.core.repository.PlayerRepository;

public class PlayerServiceImpl implements PlayerService {
	
	private static Logger LOG = LoggerFactory.getLogger(PlayerServiceImpl.class);
	
	@Autowired
	private PlayerRepository playerRepo;
	
	public PlayerServiceImpl() { }
	
	@Override
	public Player getPlayer(String id) {
		
    	LOG.debug("getPlayer(), id=" + id + ", with repository: " + playerRepo);
		Player p = playerRepo.findOne(id);
		LOG.debug("p = " + p);
		
		return p;
	}

	@Override
	public List<Player> getAllPlayers() {
		
		LOG.debug("getAllPlayers(), with repository: " + playerRepo);
		List<Player> players = playerRepo.findAll();
		LOG.debug("# of players found: " + players.size());
		return players;
	}

}
