package com.grk.core.domain.fixture;

import java.util.ArrayList;
import java.util.List;

import com.grk.core.domain.Parent;
import com.grk.core.domain.Player;

public class PlayersFixture {

	public static Player standardPlayer() {
		Player p = new Player();
		p.setId("100");
		p.setFirstName("pFirstname");
		p.setLastName("pLastname");
		
		Parent p1 = new Parent();
		p1.setName("Parent_Name");
		p1.setPhone("512-335-3211");
		p1.setEmail("gkron66@gmail.com");
		List<Parent> pl = new ArrayList<Parent>();
		pl.add(p1);
		p.setParents(pl);
		
		return p;
	}
	
	public static Player standardPlayerNotFound() {
		
		return null;
	}

	public static List<Player> standardListOfPlayers() {
		Player p = new Player();
		p.setId("100");
		p.setFirstName("pFirstname");
		p.setLastName("pLastname");
		
		List<Player> pl = new ArrayList<Player>();;
		pl.add(p);
		
		return pl;
	}
}
