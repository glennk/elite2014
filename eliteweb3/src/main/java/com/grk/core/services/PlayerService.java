package com.grk.core.services;

import java.util.List;

import com.grk.core.domain.Player;

public interface PlayerService {

	public Player getPlayer(String id);
	
	public List<Player> getAllPlayers();
}
