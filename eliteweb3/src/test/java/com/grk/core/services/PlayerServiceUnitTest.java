package com.grk.core.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.grk.core.domain.Player;
import com.grk.core.domain.Team;
import com.grk.core.event.CreatePlayerEvent;
import com.grk.core.event.PlayerDetails;
import com.grk.core.repository.PlayerRepository;
import com.grk.core.repository.TeamRepository;

public class PlayerServiceUnitTest {

	PlayerServiceImpl serviceUnderTest;
	
	PlayerRepository mockPlayerRepo;
	TeamRepository mockTeamRepo;
	
	@Before
	public void setUp() throws Exception {
		mockPlayerRepo = mock(PlayerRepository.class);
		mockTeamRepo = mock(TeamRepository.class);
		serviceUnderTest = new PlayerServiceImpl(mockPlayerRepo, mockTeamRepo);
	}

	@Test
	public void testCreateNewPlayer() {
		when(mockPlayerRepo.save(any(Player.class))).thenReturn(new Player());
		
		CreatePlayerEvent evt = new CreatePlayerEvent(new PlayerDetails());
		
		serviceUnderTest.createPlayer(evt);
		
		verify(mockPlayerRepo).save(any(Player.class));
		verifyNoMoreInteractions(mockPlayerRepo);
	}

	@Test
	public void testCreateNewPlayerValidTeamId() {
		when(mockTeamRepo.findOne(any(String.class))).thenReturn(new Team());
		when(mockPlayerRepo.save(any(Player.class))).thenReturn(new Player());
		
		PlayerDetails playerDetails = new PlayerDetails();
		playerDetails.setTeam_id("abcdefg");
		CreatePlayerEvent evt = new CreatePlayerEvent(playerDetails);
		
		serviceUnderTest.createPlayer(evt);
		verify(mockPlayerRepo).save(any(Player.class));
		verifyNoMoreInteractions(mockPlayerRepo);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateNewPlayerInvalidTeamId() {
		when(mockTeamRepo.findOne(any(String.class))).thenReturn(null);
		
		PlayerDetails playerDetails = new PlayerDetails();
		playerDetails.setTeam_id("abcdefg");
		CreatePlayerEvent evt = new CreatePlayerEvent(playerDetails);
		
		serviceUnderTest.createPlayer(evt);
	}
}
