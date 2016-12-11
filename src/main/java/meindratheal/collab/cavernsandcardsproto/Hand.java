package meindratheal.collab.cavernsandcardsproto;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * A hand is a list of cards, in a particular order. A hand starts empty. A hand
 * supports random access insertion, peeking and removal. Cards in a hand are
 * assumed to be held from left (index 0) to right (index {@code size() - 1}.
 * The following operations are supported:
 * <ul><li>The hand size can be queried with {@link #size()}.</li>
 * <li>A card can be added to the hand in any position with
 * {@link #add(Card, int)}, or added to the end with {@link #add(Card}).</li>
 * <li>Any card in the hand can be looked at without removal with
 * {@link #peek(int)}, or removed with {@link remove(int)}.</li>
 * </ul>
 * @author Meindratheal
 */
public final class Hand
{
	private final List<Card> cards;

	public Hand()
	{
		this.cards = new ArrayList<>();
	}

	public int size()
	{
		return cards.size();
	}

	public void add(final Card card)
	{
		cards.add(checkNotNull(card, "card"));
	}

	//Throws IOOBE if out of range. Moves cards along if inserted in the middle.
	public void add(final Card card, final int idx)
	{
		checkPositionIndex(idx, size(), "idx out of range");
		cards.add(idx, checkNotNull(card, "card"));
	}

	//Throws IOOBE if out of range.
	public Card peek(final int idx)
	{
		checkElementIndex(idx, size(), "idx out of range");
		return cards.get(idx);
	}

	//Throws IOOBE if out of range.
	public Card remove(final int idx)
	{
		checkElementIndex(idx, size(), "idx out of range");
		return cards.remove(idx);
	}
}
