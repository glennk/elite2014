package com.grk.rest.controller.fixture;

import java.util.ArrayList;
import java.util.List;

import com.grk.core.domain.Parent;
import com.grk.core.domain.Player;
//import com.grk.rest.domain.ContactInfo;
//import com.grk.rest.domain.PlayerSummary;

public class RestDataFixture {
	
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
		List<Player> pl = new ArrayList<Player>();;
		Player p1 = standardPlayer();
		pl.add(p1);
		Player p2 = standardPlayer();
		p2.setId("101");
		pl.add(p2);
		
		return pl;
	}
//
//	public static PlayerSummary standardPlayer() {
//		PlayerSummary p = new PlayerSummary();
//		p.setId("100");
//		p.setFirstname("pFirstname");
//		p.setLastname("pLastname");
//		
//		ContactInfo p1 = new ContactInfo();
//		p1.setName("Parent_Name");
//		p1.setPhone("512-335-3211");
//		p1.setEmail("gkron66@gmail.com");
//		List<ContactInfo> pl = new ArrayList<ContactInfo>();
//		pl.add(p1);
//		p.setContacts(pl);
//		
//		return p;
//	}
//	
//	public static PlayerSummary standardPlayerNotFound() {
//		
//		return null;
//	}
//
//	public static List<PlayerSummary> standardListOfPlayers() {
//		List<PlayerSummary> pl = new ArrayList<PlayerSummary>();;
//		PlayerSummary p1 = standardPlayer();
//		pl.add(p1);
//		PlayerSummary p2 = standardPlayer();
//		p2.setId("101");
//		pl.add(p2);
//		
//		return pl;
//	}
}
