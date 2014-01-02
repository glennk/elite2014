package com.grk.core.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Player {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Parent> parents;
//    @DBRef
//    private Team team;

	public Player() { }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Parent> getParents() {
		return parents;
	}


	public void setParents(List<Parent> parents) {
		this.parents = parents;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", parents=" + parents + "]";
	}


//	public Team getTeam() {
//		return team;
//	}
//
//	public void setTeam(Team team) {
//		this.team = team;
//	}

}
