package com.grk.config;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.grk.config.MongoConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfig.class })
public abstract class AbstractIntegrationTest {

	@Before
	public void setUp() throws Exception {

		Mongo mongo = new Mongo();
		DB database = mongo.getDB("test");

		// Teams
		DBCollection teams = database.getCollection("team");
		teams.drop();

		DBObject team1 = new BasicDBObject("id", "ABC-XYZ");
		team1.put("name", "Austin Elite 15U");
		team1.put("level", "Major");
		team1.put("age", "15U");
		teams.insert(team1);

		// Players
		DBCollection players = database.getCollection("player");
		players.drop();

		BasicDBObject player1 = new BasicDBObject("firstname", "Michael");
		player1.put("lastname", "lastname-via-abstract");
		player1.put("email", "mk@gmail.com");
		// player1.put("team", new DBRef(database, "team", team1.get("_id")));

		players.insert(player1);
	}

	@After
	public void tearDown() throws Exception {
		Mongo mongo = new Mongo();
		DB database = mongo.getDB("test");

		// Teams
		DBCollection teams = database.getCollection("team");
		teams.drop();

		// Players
		DBCollection players = database.getCollection("player");
		players.drop();
	}
}
