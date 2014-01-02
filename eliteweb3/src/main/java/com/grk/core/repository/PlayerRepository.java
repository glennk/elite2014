package com.grk.core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grk.core.domain.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

    public Player findByFirstName(String firstName);
    public List<Player> findByLastName(String lastName);

}
