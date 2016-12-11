package meindratheal.collab.cavernsandcardsproto;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * A discard pile is a list of cards, in a particular order. A discard pile
 * starts empty. Unlike a deck, a discard pile allows random access removal and
 * peeking. The following operations are supported:
 * <ul><li>The discard pile size can be queried with {@link #size()}.</li>
 * <li>A card can be placed on the top of the discard pile with
 * {@link #discard(Card)}.</li>
 * <li>Any card in the pile can be looked at without removal with
 * {@link #peek(int)}, or taken from the pile with {@link remove(int)}. The
 * indices count from the top of the discard pile.</li>
 * </ul>
 * @author Meindratheal
 */
public final class DiscardPile
{
	//TODO: Since the most common operation is to discard a card, consider
	//using a reverse list view. That way, additions to the start of the list
	//actually add to the bottom, avoiding the need to move the other elements.
	private final List<Card> cards;
	
	public DiscardPile()
	{
		this.cards = new ArrayList<>();
	}

	public int size()
	{
		return cards.size();
	}

	public void discard(final Card card)
	{
		//Remember, adding to the top.
		cards.add(0, checkNotNull(card, "card"));
	}

	public Card peek(final int idx)
	{
		checkElementIndex(idx, size(), "idx out of range");
		return cards.get(idx);
	}

	public Card remove(final int idx)
	{
		checkElementIndex(idx, size(), "idx out of range");
		return cards.remove(idx);
	}
}
