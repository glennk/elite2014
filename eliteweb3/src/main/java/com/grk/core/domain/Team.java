package com.grk.core.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Team {

	@Id
	private String id;
	private String name;
	private String age;
	private String level;
//	@DBRef
	private List<String> players;

	public Team() { }
	
	public Team(String name, String age, String level) {
		super();
		this.name = name;
		this.age = age;
		this.level = level;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", age=" + age + ", level=" + level + "]";
	}
	
	
}
