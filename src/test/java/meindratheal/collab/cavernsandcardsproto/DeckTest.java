package meindratheal.collab.cavernsandcardsproto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 *
 * @author Meindratheal
 */
public class DeckTest
{
	@Test
	public void testEmptyConstructor()
	{
		final Deck deck = new Deck();
		assertTrue(deck.isEmpty());
		assertEquals(0, deck.size());
		//Should be safe to shuffle an empty deck.
		deck.shuffle();
		try
		{
			deck.drawCard();
			fail("expected IllegalStateException");
		}
		catch(final IllegalStateException ex)
		{
			//Expected.
		}
	}

	@Test
	public void testListConstructor()
	{
		final List<Card> cardList = new ArrayList<>(
				Arrays.asList(new AttackCard("A", 1),
							  new AttackCard("B", 2),
							  new AttackCard("C", 3),
							  new AttackCard("D", 4),
							  new AttackCard("E", 5)));
		final Deck deck = new Deck(cardList);
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
	
	@Test public void testCopyConstructor()
	{
		final List<Card> cardList = new ArrayList<>(
				Arrays.asList(new AttackCard("A", 1),
							  new AttackCard("B", 2),
							  new AttackCard("C", 3),
							  new AttackCard("D", 4),
							  new AttackCard("E", 5)));
		final Deck deck = new Deck(cardList);
		assertFalse(deck.isEmpty());
		assertEquals(cardList.size(), deck.size());
		final Deck copy = new Deck(deck);
		while(!copy.isEmpty())
		{
			assertEquals(deck.size(), copy.size());
			assertEquals(deck.drawCard(), copy.drawCard());
		}
	}

	@Test
	public void testInsertCard()
	{
		final Deck deck = new Deck();
		deck.insertCardTop(new AttackCard("A", 1));
		deck.insertCardBottom(new AttackCard("B", 2));
		deck.insertCardTop(new AttackCard("C", 3));
		deck.insertCardTop(new AttackCard("D", 4));
		deck.insertCardBottom(new AttackCard("E", 5));
		assertEquals(5, deck.size());
		//Deck should be DCABE from the top.
		assertEquals(new AttackCard("D", 4), deck.drawCard());
		assertEquals(new AttackCard("C", 3), deck.drawCard());
		assertEquals(new AttackCard("A", 1), deck.drawCard());
		assertEquals(new AttackCard("B", 2), deck.drawCard());
		assertEquals(new AttackCard("E", 5), deck.drawCard());
		assertEquals(0, deck.size());
		assertTrue(deck.isEmpty());
	}
}
