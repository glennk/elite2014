package com.grk.rest.domain;

import java.util.ArrayList;
import java.util.List;

import com.grk.core.domain.Parent;
import com.grk.core.domain.Player;

public class PlayerSummary {

	private long version = 1L;

	private String id;
	private String firstname;
	private String lastname;
	private String jerseyNum;
	private List<ContactInfo> contacts = new ArrayList<ContactInfo>();

	public static PlayerSummary fromCorePlayer(Player p) {
		PlayerSummary pr = new PlayerSummary();
		if (p != null) {
			pr.id = p.getId();
			pr.firstname = p.getFirstName();
			pr.lastname = p.getLastName();
			if (p.getParents() != null) {
				for (Parent parent : p.getParents()) {
					ContactInfo ci = ContactInfo.copyFromParent(parent);
					pr.contacts.add(ci);
				}
			}
		}
		return pr;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getJerseyNum() {
		return jerseyNum;
	}

	public void setJerseyNum(String jerseyNum) {
		this.jerseyNum = jerseyNum;
	}

	public List<ContactInfo> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactInfo> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "PlayerSummary [version=" + version + ", id=" + id
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", jerseyNum=" + jerseyNum + ", contacts=" + contacts + "]";
	}
}