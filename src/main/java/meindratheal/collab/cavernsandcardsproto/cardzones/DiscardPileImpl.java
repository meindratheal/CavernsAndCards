package meindratheal.collab.cavernsandcardsproto.cardzones;

import java.util.ArrayList;
import java.util.List;
import meindratheal.collab.cavernsandcardsproto.Card;

import static com.google.common.base.Preconditions.*;

/**
 * Basic implementation of the {@link ModifiableDiscardPile} interface. Provides
 * a default constructor that creates an empty discard pile.
 * @author Meindratheal
 */
public final class DiscardPileImpl implements ModifiableDiscardPile
{
	//TODO: Since the most common operation is to discard a card, consider
	//using a reverse list view. That way, additions to the start of the list
	//actually add to the bottom, avoiding the need to move the other elements.
	private final List<Card> cards;
	
	public DiscardPileImpl()
	{
		this.cards = new ArrayList<>();
	}

	@Override
	public int size()
	{
		return cards.size();
	}

	@Override
	public void discard(final Card card)
	{
		//Remember, adding to the top.
		cards.add(0, checkNotNull(card, "card"));
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
