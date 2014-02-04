package com.grk.rest.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.grk.rest.controller.fixture.RestDataFixture;
import com.grk.rest.domain.TeamSummary;

@Ignore
public class TeamTests {

	@Test
	public void thatTeamsCanBeAddedAndQueried() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				RestDataFixture.standardTeamJSON(), headers);

		ResponseEntity<TeamSummary> entity = template.postForEntity(
				"http://localhost:8080/teams", requestEntity,
				TeamSummary.class);
		
		String path = entity.getHeaders().getLocation().getPath();
		
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
		assertTrue(path.startsWith("/teams/"));
		TeamSummary team = entity.getBody();
		
		System.out.println("The Team ID is: " + team.getId());
		System.out.println("The Location of resource is: " + entity.getHeaders().getLocation());
	}

}
