package meindratheal.collab.cavernsandcardsproto.cardzones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import meindratheal.collab.cavernsandcardsproto.Card;

import static com.google.common.base.Preconditions.*;

/**
 * Basic implementation of the {@link ModifiableDeck} interface. Provides a
 * default constructor that creates an empty deck, and a constructor that copies
 * a list of cards.
 * @author Meindratheal
 */
public final class DeckImpl implements ModifiableDeck
{
	private final List<Card> cards;

	public DeckImpl()
	{
		this.cards = new ArrayList<>();
	}

	public DeckImpl(final List<Card> otherCards)
	{
		this.cards = new ArrayList<>(checkNotNull(otherCards, "otherCards"));
	}

	@Override
	public int size()
	{
		return cards.size();
	}

	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public Card drawCard()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("can't draw from empty deck");
		}
		return cards.remove(0);
	}

	@Override
	public void insertCardTop(final Card card)
	{
		checkNotNull(card, "card");
		cards.add(0, card);
	}

	@Override
	public void insertCardBottom(final Card card)
	{
		checkNotNull(card, "card");
		cards.add(card);
	}

	@Override
	public void shuffle()
	{
		Collections.shuffle(cards);
	}

	@Override
	public ModifiableDeck copy()
	{
		return new DeckImpl(cards);
	}
}
