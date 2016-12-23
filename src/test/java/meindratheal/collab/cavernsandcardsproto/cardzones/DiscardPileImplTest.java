package meindratheal.collab.cavernsandcardsproto.cardzones;

import meindratheal.collab.cavernsandcardsproto.AttackCard;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Meindratheal
 */
public class DiscardPileImplTest
{

	@Test
	public void testDefaultConstructor()
	{
		final DiscardPileImpl pile = new DiscardPileImpl();
		assertEquals(0, pile.size());
		//Try peeking and removing.
		try
		{
			pile.peek(0);
			fail("expected IndexOutOfBoundsException");
		}
		catch(final IndexOutOfBoundsException ex)
		{
			//Expected.
		}
		try
		{
			pile.remove(0);
			fail("expected IndexOutOfBoundsException");
		}
		catch(final IndexOutOfBoundsException ex)
		{
			//Expected.
		}
	}

	@Test
	public void testDiscardCards()
	{
		final DiscardPileImpl pile = new DiscardPileImpl();
		pile.discard(new AttackCard("A", 1, 1));
		assertEquals(1, pile.size());
		pile.discard(new AttackCard("B", 2, 2));
		assertEquals(2, pile.size());
		pile.discard(new AttackCard("C", 3, 3));
		assertEquals(3, pile.size());
		assertEquals(new AttackCard("C", 3, 3), pile.peek(0));
		assertEquals(new AttackCard("B", 2, 2), pile.peek(1));
		assertEquals(new AttackCard("A", 1, 1), pile.peek(2));
	}

	@Test
	public void testRemoveCards()
	{
		final DiscardPileImpl pile = new DiscardPileImpl();
		pile.discard(new AttackCard("A", 1, 1));
		pile.discard(new AttackCard("B", 2, 2));
		pile.discard(new AttackCard("C", 3, 3));
		pile.discard(new AttackCard("D", 4, 4));
		pile.discard(new AttackCard("E", 5, 5));
		pile.discard(new AttackCard("F", 6, 6));
		//Remove from the top.
		assertEquals(new AttackCard("F", 6, 6), pile.remove(0));
		assertEquals(5, pile.size());
		assertEquals(new AttackCard("E", 5, 5), pile.peek(0));
		assertEquals(new AttackCard("D", 4, 4), pile.peek(1));
		assertEquals(new AttackCard("C", 3, 3), pile.peek(2));
		assertEquals(new AttackCard("B", 2, 2), pile.peek(3));
		assertEquals(new AttackCard("A", 1, 1), pile.peek(4));
		//Remove from the bottom.
		assertEquals(new AttackCard("A", 1, 1), pile.remove(pile.size() - 1));
		assertEquals(4, pile.size());
		assertEquals(new AttackCard("E", 5, 5), pile.peek(0));
		assertEquals(new AttackCard("D", 4, 4), pile.peek(1));
		assertEquals(new AttackCard("C", 3, 3), pile.peek(2));
		assertEquals(new AttackCard("B", 2, 2), pile.peek(3));
		//Remove from the middle.
		assertEquals(new AttackCard("D", 4, 4), pile.remove(1));
		assertEquals(3, pile.size());
		assertEquals(new AttackCard("E", 5, 5), pile.peek(0));
		assertEquals(new AttackCard("C", 3, 3), pile.peek(1));
		assertEquals(new AttackCard("B", 2, 2), pile.peek(2));
	}
}
