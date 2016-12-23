package meindratheal.collab.cavernsandcardsproto.cardzones;

/**
 * Represents a read-only view of a deck of cards. A deck is a list of cards, in
 * a particular order. The deck can either be initialised with no data (in which
 * case it starts empty), or a List of Cards (with the first being the top
 * card). The following operations are supported:
 * <ul><li>The deck size can be queried with {@link #size()}, and the
 * {@link #isEmpty()} method provides a shorthand for testing if there are cards
 * left in the deck.</li>
 * <li>The deck can be {@link #copy() copied}, creating a new independent deck
 * containing the same cards.
 * </ul>
 * @author Meindratheal
 * @see ModifiableDeck
 * @todo We may need a peek method in future.
 */
public interface ReadableDeck
{
	/**
	 * Gets the number of cards in this deck. Always non-negative.
	 * @return The number of cards in this deck.
	 */
	int size();

	/**
	 * Convenience method to check if this deck is empty (contains no cards).
	 * Shorthand for {@code size() == 0}.
	 * @return True if the deck is empty, false otherwise.
	 */
	boolean isEmpty();

	/**
	 * Creates an independent copy of this deck, containing the same cards in
	 * the same order. The class of the returned deck may or may not be the same
	 * as the class of the copied deck.
	 * @return A copy of this deck.
	 */
	ModifiableDeck copy();
}
