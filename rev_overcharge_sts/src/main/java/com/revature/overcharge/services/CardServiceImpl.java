package com.revature.overcharge.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.overcharge.beans.Card;
import com.revature.overcharge.beans.Deck;
import com.revature.overcharge.repositories.CardRepo;
import com.revature.overcharge.repositories.DeckRepo;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    CardRepo cr;
    
    @Autowired
    DeckRepo dr;

    @Autowired
    ObjectiveService os;
    
    @Autowired
    DeckService ds;
    
    @Override
    public Card addCard(int deckId, Card c) {
        if (cr.existsById(c.getId())) {
            log.warn("Card id is invalid for add");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            c.setDeck(ds.getDeck(deckId));
            c.setCreatedOn(new Date().getTime());
            c = cr.save(c);
            os.setAdd4CardsDaily(deckId, c);
            
            Deck d = ds.getDeck(deckId);
            if(d.getStatus()!= 1) {
            d.setStatus(1);
            dr.save(d);
            }
            
            return c;
        }
    }

    @Override
    public Card getCard(int id) {
        if (cr.existsById(id)) {
            return cr.findById(id).get();
        } else {
            log.warn("Card id is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
	public Card updateCard(int deckId, Card newCard) {
		log.trace("updateCard(): newCard: [" + newCard.toString() + "]");

		if (dr.existsById(deckId)) {
			if (cr.existsById(newCard.getId())) {
				
				
				Deck objDeck = ds.getDeck(deckId);
				
				
				newCard.setDeck(ds.getDeck(deckId));
				
				Card objNewCard = cr.save(newCard);
				
				objDeck.setStatus(1);
                dr.save(objDeck);
				
				return objNewCard;
				

			} else {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}else {
			log.debug("updateCard(): NO deck found for this card by deck id: [" + deckId + "]");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}

    @Override
    public boolean deleteCard(int id) {
        if (cr.existsById(id)) {
            cr.deleteById(id);
            return true;
        } else {
            log.warn("Card id is invalid for delete");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Card> getCardsByDeckId(int deckId) {
        if (cr.existsByDeckId(deckId)) {
            return cr.findByDeckId(deckId);
        } else {
            log.warn("There are no cards for the given deck id");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<Card> getAllCards() {
        return (List<Card>) cr.findAll();
    }

}