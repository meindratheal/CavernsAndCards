package meindratheal.collab.cavernsandcardsproto.cardzones;

import meindratheal.collab.cavernsandcardsproto.Card;

/**
 * Represents a hand of cards. A hand is a list of cards, in a particular order.
 * A hand starts empty. A hand supports random access insertion, peeking and
 * removal. Cards in a hand are assumed to be held from left (index 0) to right
 * (index {@code size() - 1}. The following operations are supported:
 * <ul><li>The hand size can be queried with {@link #size()}.</li>
 * <li>A card can be added to the hand in any position with
 * {@link #add(Card, int)}, or added to the end with {@link #add(Card}).</li>
 * <li>Any card in the hand can be looked at without removal with
 * {@link #peek(int)}, or removed with {@link remove(int)}.</li>
 * </ul>
 * @author Meindratheal
 * @see ReadableHand
 */
public interface ModifiableHand extends ReadableHand
{
	/**
	 * Adds a card to the end of the hand.
	 * @param card The card to add.
	 */
	void add(Card card);

	/**
	 * Adds a card to the hand at the given index. If there is already a card at
	 * that index, then that card and all subsequent cards in the hand are moved
	 * along an index.
	 * @param card The card to add.
	 * @param idx The index to add the card at.
	 * @throws IndexOutOfBoundsException If {@code idx < 0} or
	 * {@code idx >= size()}.
	 */
	void add(Card card, int idx);

	//Throws IOOBE if out of range.
	Card remove(int idx);
}
