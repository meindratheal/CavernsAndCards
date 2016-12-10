package meindratheal.collab.cavernsandcardsproto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * A deck is a list of cards, in a particular order. The deck can either be
 * initialised with no data (in which case it starts empty), a List of Cards
 * (with the first being the top card), or as a copy of another Deck. The
 * following operations are supported:
 * <ul><li>The deck size can be queried with {@link #size()}, and the
 * {@link #isEmpty()} method provides a shorthand for testing if there are cards
 * left in the deck.</li>
 * <li>The top card in the deck can be {@link #drawCard() drawn}, if the deck is
 * not empty.</li>
 * <li>A card can be inserted into the deck, either at the
 * {@link #insertCardTop(Card) top} or the
 * {@link #insertCardBottom(Card) bottom}.</li>
 * <li>The deck can be {@link #shuffle() shuffled}.</li>
 * </ul>
 * <p>
 * @author Meindratheal
 */
public final class Deck
{
	private final List<Card> cards;

	public Deck()
	{
		this.cards = new ArrayList<>();
	}

	public Deck(final List<Card> otherCards)
	{
		this.cards = new ArrayList<>(checkNotNull(otherCards, "otherCards"));
	}
	
	public Deck(final Deck other)
	{
		this.cards = new ArrayList<>(checkNotNull(other, "other").cards);
	}

	public int size()
	{
		return cards.size();
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	//Throws ISE if empty.
	public Card drawCard()
	{
		checkState(!isEmpty(), "can't drawn from an empty deck");
		return cards.remove(0);
	}

	public void insertCardTop(final Card card)
	{
		checkNotNull(card, "card");
		cards.add(0, card);
	}

	public void insertCardBottom(final Card card)
	{
		checkNotNull(card, "card");
		cards.add(card);
	}

	public void shuffle()
	{
		Collections.shuffle(cards);
	}
}
