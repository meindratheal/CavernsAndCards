package meindratheal.collab.cavernsandcardsproto.cardzones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import meindratheal.collab.cavernsandcardsproto.AttackCard;
import meindratheal.collab.cavernsandcardsproto.Card;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 *
 * @author Meindratheal
 */
public class DeckImplTest
{

	@Test
	public void testEmptyConstructor()
	{
		final DeckImpl deck = new DeckImpl();
		assertTrue(deck.isEmpty());
		assertEquals(0, deck.size());
		//Should be safe to shuffle an empty deck.
		deck.shuffle();
		try
		{
			deck.drawCard();
			fail("expected NoSuchElementException");
		}
		catch(final NoSuchElementException ex)
		{
			//Expected.
		}
	}

	@Test
	public void testListConstructor()
	{
		final List<Card> cardList = new ArrayList<>(
				Arrays.asList(new AttackCard("A", 1, 1),
							  new AttackCard("B", 2, 2),
							  new AttackCard("C", 3, 3),
							  new AttackCard("D", 4, 4),
							  new AttackCard("E", 5, 5)));
		final DeckImpl deck = new DeckImpl(cardList);
		assertFalse(deck.isEmpty());
		assertEquals(cardList.size(), deck.size());
		deck.shuffle();
		for(int i = 0; i < cardList.size(); ++i)
		{
			final Card card = deck.drawCard();
			assertThat(cardList, hasItem(equalTo(card)));
			cardList.remove(card);
		}
	}

	@Test
	public void testInsertCard()
	{
		final DeckImpl deck = new DeckImpl();
		deck.insertCardTop(new AttackCard("A", 1, 1));
		deck.insertCardBottom(new AttackCard("B", 2, 2));
		deck.insertCardTop(new AttackCard("C", 3, 3));
		deck.insertCardTop(new AttackCard("D", 4, 4));
		deck.insertCardBottom(new AttackCard("E", 5, 5));
		assertEquals(5, deck.size());
		//Deck should be DCABE from the top.
		assertEquals(new AttackCard("D", 4, 4), deck.drawCard());
		assertEquals(new AttackCard("C", 3, 3), deck.drawCard());
		assertEquals(new AttackCard("A", 1, 1), deck.drawCard());
		assertEquals(new AttackCard("B", 2, 2), deck.drawCard());
		assertEquals(new AttackCard("E", 5, 5), deck.drawCard());
		assertEquals(0, deck.size());
		assertTrue(deck.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testDrawFromEmptyDeck()
	{
		new DeckImpl().drawCard();
	}

	@Test
	public void testCopy()
	{
		final DeckImpl original = new DeckImpl(Arrays.asList(
				new AttackCard("A", 1, 1),
				new AttackCard("B", 2, 2),
				new AttackCard("C", 3, 3),
				new AttackCard("D", 4, 4),
				new AttackCard("E", 5, 5),
				new AttackCard("F", 6, 6),
				new AttackCard("G", 7, 7),
				new AttackCard("H", 8, 8)));
		final ModifiableDeck copy = original.copy();
		
		//Remove from original, check the copy is unmodified.
		original.drawCard();
		assertEquals(original.size() + 1, copy.size());
		//Bring back into sync.
		copy.drawCard();
		assertEquals(original.size(), copy.size());
		//Draw from copy, check the original is unmodified.
		copy.drawCard();
		assertEquals(original.size() - 1, copy.size());
		//Bring back into sync.
		original.drawCard();
		assertEquals(original.size(), copy.size());
		//Draw all remaining cards from both, check they're equal.
		while(!original.isEmpty())
		{
			assertEquals(original.drawCard(), copy.drawCard());
		}
	}
}
