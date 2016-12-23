package meindratheal.collab.cavernsandcardsproto.cardzones;

import meindratheal.collab.cavernsandcardsproto.Card;

/**
 * Represents a read-only view of a discard pile. A discard pile is a list of
 * cards, in a particular order. A discard pile starts empty. Unlike a deck, a
 * discard pile allows random access to its elements. The following operations
 * are supported:
 * <ul><li>The discard pile size can be queried with {@link #size()}.</li>
 * <li>Any card in the pile can be looked at without removal with
 * {@link #peek(int)}.. The indices count from the top of the discard pile.</li>
 * </ul>
 * @author Meindratheal
 * @see ModifiableDiscardPile
 */
public interface ReadableDiscardPile
{
	/**
	 * Gets the number of cards in this discard pile. Always non-negative.
	 * @return The number of cards in this discard pile.
	 */
	int size();

	/**
	 * Returns (without removal) the card at the given index. Index 0 is the most recently
	 * discarded card.
	 * @param idx The index of the card to return.
	 * @return The card at the given index.
	 * @throws IndexOutOfBoundsException If {@code idx < 0} or
	 * {@code idx >= size()}.
	 */
	Card peek(int idx);
}
