package meindratheal.collab.cavernsandcardsproto.cardzones;

import meindratheal.collab.cavernsandcardsproto.Card;

/**
 * Represents a deck of cards. A deck is a list of cards, in a particular order.
 * The deck can either be initialised with no data (in which case it starts
 * empty), or a List of Cards (with the first being the top card). The following
 * operations are supported:
 * <ul><li>The deck size can be queried with {@link #size()}, and the
 * {@link #isEmpty()} method provides a shorthand for testing if there are cards
 * left in the deck.</li>
 * <li>The top card in the deck can be {@link #drawCard() drawn}, if the deck is
 * not empty.</li>
 * <li>A card can be inserted into the deck, either at the
 * {@link #insertCardTop(Card) top} or the
 * {@link #insertCardBottom(Card) bottom}.</li>
 * <li>The deck can be {@link #shuffle() shuffled}.</li>
 * </ul>
 * @author Meindratheal
 * @see ReadableDeck
 */
public interface ModifiableDeck extends ReadableDeck
{
	/**
	 * Removes and returns the top card from the deck.
	 * @return The top card of the deck.
	 * @throws java.util.NoSuchElementException If the deck is
	 * {@link #isEmpty() empty}.
	 */
	Card drawCard();

	/**
	 * Inserts a card at the top of the deck.
	 * @param card The card to insert.
	 */
	void insertCardTop(final Card card);

	/**
	 * Inserts a card at the bottom of the deck.
	 * @param card
	 */
	void insertCardBottom(final Card card);

	/**
	 * Shuffles the deck.
	 */
	void shuffle();
}
