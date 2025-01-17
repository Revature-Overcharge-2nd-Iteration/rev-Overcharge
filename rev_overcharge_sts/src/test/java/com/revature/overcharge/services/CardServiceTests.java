package com.revature.overcharge.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.revature.overcharge.beans.Card;
import com.revature.overcharge.beans.Deck;
import com.revature.overcharge.beans.User;
import com.revature.overcharge.repositories.CardRepo;
import com.revature.overcharge.repositories.DeckRepo;
import com.revature.overcharge.repositories.UserRepo;

@SpringBootTest(classes = com.revature.overcharge.application.RevOverchargeStsApplication.class)
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class CardServiceTests {

	@Autowired
	public CardService cs;
	@MockBean
	CardRepo cr;
	
	@Autowired
	public DeckService ds;
	
	@MockBean
	DeckRepo dr;
	
	@Autowired
	public UserService us;
	
	@MockBean
	UserRepo ur;

	@Test
	@Transactional
	void addCardTest() {
		// Card(String question, String answer, Long createdOn)
		// Card(int id, Deck deck, String question, String answer, Long createdOn)
		List<Card> cards = new ArrayList<Card>();
		User user = new User("username", "password", 0, 0, (long)100);
		Deck deck = new Deck(user, "title", (long)100, cards,  null, null);
		
		Optional<User> optionalUser = Optional.of(user);
		Card card = new Card("whats your name", "my name is ahmed", null);
		// Card(int id, Deck deck, String question, String answer, Long createdOn)
		Mockito.when(cr.save(card)).thenReturn(new Card(1, deck, "whats your name", "my name is ahmed", null));
		Mockito.when(dr.existsById(1)).thenReturn(true);
		Mockito.when(dr.findById(1)).thenReturn(deck);
		Mockito.when(ur.existsById(user.getId())).thenReturn(true);
		Mockito.when(ur.findById(user.getId())).thenReturn(optionalUser);
		card = cs.addCard(1, card);
		
		Assertions.assertEquals("whats your name", card.getQuestion());
	}
	
	@Test
	@Transactional
	void addCardFailure() {
		Deck deck = new Deck();
		Card card = new Card("whats your name", "my name is ahmed", null);
		Mockito.when(cr.existsById(card.getId())).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> {
            cs.addCard(1, card);
        });
	}

	@Test
	@Transactional
	void getCardTest() {
		Deck deck = new Deck();
		Card card = new Card(1, "whats your name", "my name is ahmed", null);

		Mockito.when(cr.existsById(card.getId())).thenReturn(true);
		Mockito.when(cr.findById(card.getId())).thenReturn(Optional.of(card));;
		
		cs.getCard(card.getId());
		
		Assertions.assertEquals("my name is ahmed", card.getAnswer());
	}
	
	@Test
	@Transactional
	void getCardFailure() {
		Deck deck = new Deck();
		Card card = new Card(1, "whats your name", "my name is ahmed", null);
		
		Mockito.when(cr.existsById(card.getId())).thenReturn(false);
		
        assertThrows(ResponseStatusException.class, () -> {
            cs.getCard(card.getId());
        });
	}

	@Test
	@Transactional
	void updateCardTest() {
		List<Card> cards = new ArrayList<Card>();
		Deck deck = new Deck(null, "title", (long)100, cards,  null, null);
		
		Card card = new Card(1, deck, "whats your lastNameAH", "my name is Elhewazy", null);
		
		Mockito.when(cr.existsById(card.getId())).thenReturn(true);
		Mockito.when(cr.save(card)).thenReturn(new Card(1, deck, "whats your lastName", "my name is Elhewazy", null));
		Mockito.when(dr.existsById(1)).thenReturn(true);
		Mockito.when(dr.findById(1)).thenReturn(deck);
		card = cs.updateCard(1, card);
		Assertions.assertEquals("whats your lastName", card.getQuestion());
		Assertions.assertEquals("my name is Elhewazy", card.getAnswer());
	}
	
	@Test
	@Transactional
	void test_updateCard_CardNoExist() {
		Deck deck = new Deck();
		Card card = new Card(1, deck, "whats your lastNameAH", "my name is Elhewazy", null);
		
		
		Mockito.when(dr.existsById(deck.getId())).thenReturn(false);
		Mockito.when(cr.existsById(card.getId())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
            cs.updateCard(1, card);
        });
		
	}
	
	@Test
	@Transactional
	void updateCardFailure() {
		Deck deck = new Deck();
		Card card = new Card(1, deck, "whats your lastNameAH", "my name is Elhewazy", null);
		
		
		Mockito.when(dr.existsById(deck.getId())).thenReturn(true);
		Mockito.when(cr.existsById(card.getId())).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> {
            cs.updateCard(1, card);
        });
	}

	@Test
	@Transactional
	void deleteCardTest() {
		Deck deck = new Deck();
		Card card = new Card(1, "whats your name", "my name is ahmed", null);

		Mockito.when(cr.existsById(card.getId())).thenReturn(true);
		
		Assertions.assertEquals(cs.deleteCard(card.getId()), true);
	}
	
	@Test
	@Transactional
	void deleteCardFailure() {
		Deck deck = new Deck();
		Card card = new Card(1, "whats your name", "my name is ahmed", null);

		Mockito.when(cr.existsById(card.getId())).thenReturn(false);
		
        assertThrows(ResponseStatusException.class, () -> {
            cs.deleteCard(card.getId());
        });
	}
	
	@Test
	@Transactional
	void getAllCardsTest() {
		Deck deck = new Deck();
		Card card = new Card(1, deck, "whats your name", "my name is ahmed", null);
		List<Card> cList = new ArrayList<Card>();
		cList.add(card);
		
		Mockito.when(cr.findAll()).thenReturn(cList);
		List<Card> actualCard = cs.getAllCards();
		
		Assertions.assertNotNull(actualCard);
	}
	
	@Test
	@Transactional
	void getCardsByDeckIdTest() {
		Deck deck = new Deck();
		deck.setId(1);
		Card cards = new Card(1, deck, "what is your name", "my name is ahmed", null);
		List<Card> cList = new ArrayList<Card>();
		cList.add(cards);
		Mockito.when(cr.existsByDeckId(1)).thenReturn(true);
		Mockito.when(cr.findByDeckId(1)).thenReturn(cList);
		List<Card> actualCard = cs.getCardsByDeckId(1);
		Assertions.assertEquals(cList, actualCard);
	}
	
	@Test
	@Transactional
	void getCardsByDeckIdFailure() {
		Mockito.when(cr.existsByDeckId(1)).thenReturn(false);
		assertThrows(ResponseStatusException.class, () ->{
			cs.getCardsByDeckId(0);
		});
	}
	
	@Test
	@Transactional
	void getDeckIdFailureTest() {
		Mockito.when(dr.existsById(1)).thenReturn(false);
		assertThrows(ResponseStatusException.class, () ->{
			ds.getDeck(1);
		});
		
	}

}