package meindratheal.collab.cavernsandcardsproto.cardzones;

import meindratheal.collab.cavernsandcardsproto.Card;

/**
 * Represents a read-only view of a hand of cards. A hand is a list of cards, in
 * a particular order. A read-only hand supports random access peeking. Cards in
 * a hand are assumed to be held from left (index 0) to right (index
 * {@code size() - 1}. The following operations are supported:
 * <ul><li>The hand size can be queried with {@link #size()}.</li>
 * <li>Any card in the hand can be looked at without removal with
 * {@link #peek(int)}, or removed with {@link remove(int)}.</li>
 * </ul>
 * @author Meindratheal
 * @see ModifiableHand
 */
public interface ReadableHand
{
	/**
	 * Gets the number of cards in this hand. Always non-negative.
	 * @return The number of cards in this hand.
	 */
	int size();

	/**
	 * Gets the card at the given index.
	 * @param idx The index to look at.
	 * @return The card at the given index.
	 * @throws IndexOutOfBoundsException If {@code idx < 0} or
	 * {@code idx >= size()}.
	 */
	Card peek(final int idx);
}
