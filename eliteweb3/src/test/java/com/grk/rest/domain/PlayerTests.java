package com.grk.rest.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.grk.core.domain.Player;
import com.grk.rest.controller.fixture.RestDataFixture;

public class PlayerTests {

	@Test
	public void thatPlayerCanConvertToPlayerSummary() {
		Player p = RestDataFixture.standardPlayer();
		
		PlayerSummary ps = PlayerSummary.fromCorePlayer(p);
		assertEquals(ps.getId(), p.getId());
		//TODO do more tests
	}
	
}
