package com.revature.overcharge.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.revature.overcharge.beans.StudiedCard;
import com.revature.overcharge.beans.StudiedCardId;
import com.revature.overcharge.repositories.StudiedCardRepo;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(classes = com.revature.overcharge.application.RevOverchargeStsApplication.class)
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class StudiedCardServiceTests {

	@Autowired
	public StudiedCardService scs;
	@MockBean
	StudiedCardRepo scr;

// 	@Test
// 	void testAddStudiedCard() {
// 		StudiedCard studiedcard = new StudiedCard(0, 0, null);

// 		Mockito.when(scr.save(studiedcard)).thenReturn(new StudiedCard(0, 0, null));
// 		studiedcard = scs.addStudiedCard(studiedcard);

// 		Assertions.assertEquals(0, studiedcard.getCardId());
// 		Assertions.assertEquals(0, studiedcard.getUserId());
// 		Assertions.assertNotEquals(1, studiedcard.getCardId());
// 		Assertions.assertNotEquals(1, studiedcard.getUserId());
// 	}

	@Test
	@Transactional
	void test_GetStudiedCards_notNull() {
		int userId = 1;
		int cardId = 1;
		StudiedCard studiedcard = new StudiedCard(1, 1, null);
		List<StudiedCard> list = new ArrayList<StudiedCard>();
		list.add(studiedcard);

		Mockito.when(scr.getByUserIdAndCardId(userId, cardId)).thenReturn(list);
		
		List<StudiedCard> actualList = scs.getStudiedCards(userId, cardId);

		Assertions.assertEquals(list, actualList);
	}
	
	@Test
	@Transactional
	void test_GetStudiedCards_ZeroCardId() {
		StudiedCard studiedcard = new StudiedCard(1, 0, null);
		List<StudiedCard> list = new ArrayList<StudiedCard>();
		list.add(studiedcard);

		Mockito.when(scr.getByUserId(1)).thenReturn(list);
		
		List<StudiedCard> actualList = scs.getStudiedCards(1, 0);

		Assertions.assertEquals(list, actualList);
	}
	
	@Test
	@Transactional
	void test_GetStudiedCards_ZeroUserId() {
		StudiedCard studiedcard = new StudiedCard(0, 1, null);
		List<StudiedCard> list = new ArrayList<StudiedCard>();
		list.add(studiedcard);

		Mockito.when(scr.getByCardId(1)).thenReturn(list);
		
		List<StudiedCard> actualList = scs.getStudiedCards(0, 1);

		Assertions.assertEquals(list, actualList);
	}
	
	@Test
	@Transactional
	void test_GetStudiedCards_ZeroCardIdAndZeroUserId() {
		StudiedCard studiedcard = new StudiedCard(0, 0, null);
		List<StudiedCard> list = new ArrayList<StudiedCard>();
		list.add(studiedcard);

		Mockito.when(scr.findAll()).thenReturn(list);
		
		List<StudiedCard> actualList = scs.getStudiedCards(0, 0);

		Assertions.assertEquals(list, actualList);
	}
	
	@Test
	@Transactional
	void test_GetStudiedCards_UserAndCardNotFound() {

		List<StudiedCard> list = new ArrayList<StudiedCard>();
		Mockito.when(scr.findAll()).thenReturn(list);
		
		

		Assertions.assertThrows(ResponseStatusException.class, () -> {
			List<StudiedCard> actualList = scs.getStudiedCards(0, 0);
		});
	}
	
	@Test
	@Transactional
	void test_addStudiedCard_positive() {
		StudiedCard studiedcard = new StudiedCard(1, 1, null);
		
		Mockito.when(scr.existsByUserIdAndCardId(1, 1)).thenReturn(false);
		Mockito.when(scr.save(studiedcard)).thenReturn(studiedcard);
		
		StudiedCard actualCard = scs.addStudiedCard(studiedcard);
		
		Assertions.assertEquals(studiedcard, actualCard);
		
	}
	
	@Test
	@Transactional
	void test_addStudiedCard_alreadyMarked() {
		StudiedCard studiedcard = new StudiedCard(1, 1, null);
		
		Mockito.when(scr.existsByUserIdAndCardId(1, 1)).thenReturn(true);
		
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			StudiedCard actualCard = scs.addStudiedCard(studiedcard);
		});
		
	}
	
	@Test
	@Transactional
	void test_getStudiedCardsByUser_positive() {
		
		StudiedCard studiedcard = new StudiedCard(1, 1, null);
		List<StudiedCard> list = new ArrayList<StudiedCard>();
		list.add(studiedcard);
		
		Mockito.when(scr.getByUserId(1)).thenReturn(list);
		
		List<StudiedCard> actualList = scs.getStudiedCardsByUser(1);
		
		Assertions.assertEquals(list, actualList);
		
	}
	
	@Test
	@Transactional
	void test_updateCard_positive() {
		StudiedCard studiedcard = new StudiedCard(1, 1, null);
		
		Mockito.when(scr.save(studiedcard)).thenReturn(studiedcard);
		
		StudiedCard actualCard = scs.updateCard(studiedcard);
		
		Assertions.assertEquals(studiedcard, actualCard);
		
	}
	
	@Test
	@Transactional
	void test_deleteStudiedCard_positive() {
	
		StudiedCardId scId= new StudiedCardId(1,1);
		
		Mockito.when(scr.existsById(scId)).thenReturn(true);
		boolean isDeleted = scs.deleteStudiedCard(scId);
		Assertions.assertEquals(isDeleted, true);
		
	}
	
	@Test
	@Transactional
	void test_deleteStudiedCard_negative() {
	
		StudiedCardId scId= new StudiedCardId(1,1);
		
		Mockito.when(scr.existsById(scId)).thenReturn(false);
		
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			boolean isDeleted = scs.deleteStudiedCard(scId);
		});
		
	}
	
	

//	@Test
//	@Transactional
//	void testDeleteStudiedCard() {
//		StudiedCard studiedcard = new StudiedCard(0, 0, null);
//		StudiedCardId studiedcardid = new StudiedCardId(studiedcard.getCardId(), studiedcard.getUserId());
//
//		Mockito.when(scr.existsById(studiedcardid)).thenReturn(true);
//
//		Assertions.assertEquals(scs.deleteStudiedCard(studiedcardid), true);
//	}
//
}
