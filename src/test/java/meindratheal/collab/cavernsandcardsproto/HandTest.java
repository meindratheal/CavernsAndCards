package meindratheal.collab.cavernsandcardsproto;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Meindratheal
 */
public class HandTest {

	@Test
	public void testDefaultConstructor()
	{
		final Hand hand = new Hand();
		assertEquals(0, hand.size());
		//Try peeking and removing.
		try
		{
			hand.peek(0);
			fail("expected IndexOutOfBoundsException");
		}
		catch(final IndexOutOfBoundsException ex)
		{
			//Expected.
		}
		try
		{
			hand.remove(0);
			fail("expected IndexOutOfBoundsException");
		}
		catch(final IndexOutOfBoundsException ex)
		{
			//Expected.
		}
	}
	
	@Test
	public void testAddCards()
	{
		final Hand hand = new Hand();
		//Add some without giving an index.
		hand.add(new AttackCard("A", 1));
		hand.add(new AttackCard("B", 2));
		hand.add(new AttackCard("C", 3));
		assertEquals(3, hand.size());
		assertEquals(new AttackCard("A", 1), hand.peek(0));
		assertEquals(new AttackCard("B", 2), hand.peek(1));
		assertEquals(new AttackCard("C", 3), hand.peek(2));
		//Add one to the end explicitly.
		hand.add(new AttackCard("D", 4), 3);
		assertEquals(4, hand.size());
		assertEquals(new AttackCard("A", 1), hand.peek(0));
		assertEquals(new AttackCard("B", 2), hand.peek(1));
		assertEquals(new AttackCard("C", 3), hand.peek(2));
		assertEquals(new AttackCard("D", 4), hand.peek(3));
		//Add one at the beginning.
		hand.add(new AttackCard("E", 5), 0);
		assertEquals(5, hand.size());
		assertEquals(new AttackCard("E", 5), hand.peek(0));
		assertEquals(new AttackCard("A", 1), hand.peek(1));
		assertEquals(new AttackCard("B", 2), hand.peek(2));
		assertEquals(new AttackCard("C", 3), hand.peek(3));
		assertEquals(new AttackCard("D", 4), hand.peek(4));
		//Add one in the middle.
		hand.add(new AttackCard("F", 6), 2);
		assertEquals(6, hand.size());
		assertEquals(new AttackCard("E", 5), hand.peek(0));
		assertEquals(new AttackCard("A", 1), hand.peek(1));
		assertEquals(new AttackCard("F", 6), hand.peek(2));
		assertEquals(new AttackCard("B", 2), hand.peek(3));
		assertEquals(new AttackCard("C", 3), hand.peek(4));
		assertEquals(new AttackCard("D", 4), hand.peek(5));
	}
	
	@Test
	public void testAddCardsInvalidPositions()
	{
		final Hand hand = new Hand();
		hand.add(new AttackCard("A", 1));
		hand.add(new AttackCard("B", 2));
		hand.add(new AttackCard("C", 3));
		try
		{
			hand.add(new AttackCard("X", 0), -1);
			fail("expected IndexOutOfBoundsException");
		}
		catch(final IndexOutOfBoundsException ex)
		{
			//Expected.
		}
		try
		{
			hand.add(new AttackCard("X", 0), 4);
			fail("expected IndexOutOfBoundsException");
		}
		catch(final IndexOutOfBoundsException ex)
		{
			//Expected.
		}
	}
	
	@Test
	public void testRemoveCards()
	{
		final Hand hand = new Hand();
		hand.add(new AttackCard("A", 1));
		hand.add(new AttackCard("B", 2));
		hand.add(new AttackCard("C", 3));
		hand.add(new AttackCard("D", 4));
		hand.add(new AttackCard("E", 5));
		hand.add(new AttackCard("F", 6));
		//Remove from the end.
		hand.remove(5);
		assertEquals(5, hand.size());
		assertEquals(new AttackCard("A", 1), hand.peek(0));
		assertEquals(new AttackCard("B", 2), hand.peek(1));
		assertEquals(new AttackCard("C", 3), hand.peek(2));
		assertEquals(new AttackCard("D", 4), hand.peek(3));
		assertEquals(new AttackCard("E", 5), hand.peek(4));
		//Remove from the start.
		hand.remove(0);
		assertEquals(4, hand.size());
		assertEquals(new AttackCard("B", 2), hand.peek(0));
		assertEquals(new AttackCard("C", 3), hand.peek(1));
		assertEquals(new AttackCard("D", 4), hand.peek(2));
		assertEquals(new AttackCard("E", 5), hand.peek(3));
		//Remove from the middle.
		hand.remove(1);
		assertEquals(3, hand.size());
		assertEquals(new AttackCard("B", 2), hand.peek(0));
		assertEquals(new AttackCard("D", 4), hand.peek(1));
		assertEquals(new AttackCard("E", 5), hand.peek(2));
		
	}
}