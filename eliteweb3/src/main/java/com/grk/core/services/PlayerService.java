package com.grk.core.services;

import com.grk.core.event.AllPlayersEvent;
import com.grk.core.event.CreatePlayerEvent;
import com.grk.core.event.PlayerCreatedEvent;
import com.grk.core.event.PlayerDetailsEvent;
import com.grk.core.event.RequestAllPlayersEvent;
import com.grk.core.event.RequestPlayerDetailsEvent;

public interface PlayerService {

	public PlayerDetailsEvent getPlayer(RequestPlayerDetailsEvent evt);
	
	public AllPlayersEvent getAllPlayers(RequestAllPlayersEvent evt);
	
	public PlayerCreatedEvent createPlayer(CreatePlayerEvent evt);
}
