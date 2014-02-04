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
import com.grk.rest.domain.PlayerSummary;

@Ignore
public class PlayerTests {

	@Test
	public void thatPlayersCanBeAddedAndQueried() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				RestDataFixture.standardPlayerJSON(), headers);

		ResponseEntity<PlayerSummary> entity = template.postForEntity(
				"http://localhost:8080/players", requestEntity,
				PlayerSummary.class);
		
		String path = entity.getHeaders().getLocation().getPath();
		
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
		assertTrue(path.startsWith("/players/"));
		PlayerSummary player = entity.getBody();
		
		System.out.println("The Player ID is: " + player.getId());
		System.out.println("The Location of resource is: " + entity.getHeaders().getLocation());

	}
}
