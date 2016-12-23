package meindratheal.collab.cavernsandcardsproto.cardzones;

import meindratheal.collab.cavernsandcardsproto.Card;

/**
 * Represents a discard pile of cards. A discard pile is a list of cards, in a
 * particular order. A discard pile starts empty. Unlike a deck, a discard pile
 * allows random access removal and peeking. The following operations are
 * supported:
 * <ul><li>The discard pile size can be queried with {@link #size()}.</li>
 * <li>A card can be placed on the top of the discard pile with
 * {@link #discard(Card)}.</li>
 * <li>Any card in the pile can be looked at without removal with
 * {@link #peek(int)}, or taken from the pile with {@link remove(int)}. The
 * indices count from the top of the discard pile.</li>
 * </ul>
 * @author Meindratheal
 * @see ReadableDiscardPile
 */
public interface ModifiableDiscardPile extends ReadableDiscardPile
{
	void discard(Card card);

	/**
	 * Removes and returns the card at the given index. Index 0 is the most
	 * recently discarded card.
	 * @param idx The index of the card to remove.
	 * @return The card at the given index.
	 * @throws IndexOutOfBoundsException If {@code idx < 0} or
	 * {@code idx >= size()}.
	 */
	Card remove(int idx);
}
