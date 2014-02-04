package com.grk.core.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import com.grk.core.event.TeamDetails;

public class Team {

	@Id
	private String id;
	private String name;
	private String age;
	private String level;

	public Team() {
	}

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

	public static Team fromTeamDetails(TeamDetails teamDetails) {
		Team t = new Team();
		BeanUtils.copyProperties(teamDetails, t);
		return t;
	}

	public TeamDetails toTeamDetails() {
		TeamDetails details = new TeamDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", age=" + age + ", level=" + level + "]";
	}
}
