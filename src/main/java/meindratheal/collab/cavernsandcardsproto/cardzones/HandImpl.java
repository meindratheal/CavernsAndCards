package meindratheal.collab.cavernsandcardsproto.cardzones;

import java.util.ArrayList;
import java.util.List;
import meindratheal.collab.cavernsandcardsproto.Card;

import static com.google.common.base.Preconditions.*;

/**
 * Basic implementation of the {@link ModifiableHand} interface. Provides a
 * default constructor that creates an empty hand.
 * @author Meindratheal
 */
public final class HandImpl implements ModifiableHand
{

	private final List<Card> cards;

	public HandImpl()
	{
		this.cards = new ArrayList<>();
	}

	@Override
	public int size()
	{
		return cards.size();
	}

	@Override
	public void add(final Card card)
	{
		cards.add(checkNotNull(card, "card"));
	}

	@Override
	public void add(final Card card, final int idx)
	{
		checkPositionIndex(idx, size(), "idx out of range");
		cards.add(idx, checkNotNull(card, "card"));
	}

	@Override
	public Card peek(final int idx)
	{
		checkElementIndex(idx, size(), "idx out of range");
		return cards.get(idx);
	}

	@Override
	public Card remove(final int idx)
	{
		checkElementIndex(idx, size(), "idx out of range");
		return cards.remove(idx);
	}
}
