package meindratheal.collab.cavernsandcardsproto.demo;

import java.util.Arrays;
import meindratheal.collab.cavernsandcardsproto.AttackCard;
import meindratheal.collab.cavernsandcardsproto.cardzones.DeckImpl;
import meindratheal.collab.cavernsandcardsproto.cardzones.ModifiableDeck;

/**
 *
 * @author Meindratheal
 */
public final class TestingCards
{
	/**
	 * Suppress default constructor.
	 */
	private TestingCards()
	{
		throw new AssertionError();
	}

	public static ModifiableDeck createDeck()
	{
		return new DeckImpl(Arrays.asList(
				new AttackCard("Sure Strike", 12, 2),
				new AttackCard("Sure Strike", 12, 2),
				new AttackCard("Sure Strike", 12, 2),
				new AttackCard("Sure Strike", 12, 2),
				new AttackCard("Power Strike", 4, 5),
				new AttackCard("Power Strike", 4, 5),
				new AttackCard("Lightning Bolt", 6, 3),
				new AttackCard("Lightning Bolt", 6, 3),
				new AttackCard("Lightning Bolt", 6, 3),
				new AttackCard("Lightning Bolt", 6, 3),
				new AttackCard("Fireball", 6, 4),
				new AttackCard("Fireball", 6, 4),
				new AttackCard("Poke", 20, 1),
				new AttackCard("Poke", 20, 1),
				new AttackCard("Poke", 20, 1),
				new AttackCard("Poke", 20, 1),
				new AttackCard("Ultima", 0, 20)
				));
	}
}
