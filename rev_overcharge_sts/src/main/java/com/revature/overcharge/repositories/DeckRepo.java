package com.revature.overcharge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.overcharge.beans.Deck;

@Repository
public interface DeckRepo extends CrudRepository<Deck, Integer> {

    public boolean existsByCreatorId(int creatorId);
    
    public List<Deck> getByCreatorId(int creatorId);
    
    @Query (value = "Select d FROM DECK d"
    				+ "JOIN d.TechTag t"
    				+ "WHERE t.id = ?1"
    				+ "ORDER BY t.tag")
    public List<Deck> getByTagId(int tagId);

}