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
import com.grk.core.event.CreateTeamEvent;
import com.grk.core.event.PlayerDetails;
import com.grk.core.event.TeamDetails;
import com.grk.core.repository.TeamRepository;

public class TeamServiceUnitTest {

	TeamServiceImpl serviceUnderTest;
	
	TeamRepository mockTeamRepo;
	
	@Before
	public void setUp() throws Exception {
		mockTeamRepo = mock(TeamRepository.class);
		serviceUnderTest = new TeamServiceImpl(mockTeamRepo);
	}

	@Test
	public void testCreateNewTeam() {
		when(mockTeamRepo.save(any(Team.class))).thenReturn(new Team());
		
		CreateTeamEvent evt = new CreateTeamEvent(new TeamDetails());
		
		serviceUnderTest.createTeam(evt);
		
		verify(mockTeamRepo).save(any(Team.class));
		verifyNoMoreInteractions(mockTeamRepo);
	}
	
//	@Test(expected=IllegalArgumentException.class)
//	public void testCreateNewPlayerInvalidTeamId() {
//		when(mockTeamRepo.findOne(any(String.class))).thenReturn(null);
//		
//		PlayerDetails playerDetails = new PlayerDetails();
//		playerDetails.setTeam_id("abcdefg");
//		CreatePlayerEvent evt = new CreatePlayerEvent(playerDetails);
//		
//		serviceUnderTest.createPlayer(evt);
//	}
}
