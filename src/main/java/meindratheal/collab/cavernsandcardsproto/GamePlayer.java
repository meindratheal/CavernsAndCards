package meindratheal.collab.cavernsandcardsproto;

import java.util.function.ToIntFunction;

import static com.google.common.base.Preconditions.*;

/**
 * Represents an actual player, that owns a Deck and controls a Creature. A
 * player has a Creature, a Deck, a Hand and a Discard Pile. The hand a discard
 * pile begin empty. At all points during the game, a card that was in the deck
 * originally will either be in the deck, hand, or discard pile.
 * @author Meindratheal
 */
public final class GamePlayer
{
	private final Creature creature;
	private final Deck deck;
	private final Hand hand;
	private final DiscardPile discards;
	private final ToIntFunction<Hand> chooseAttackCardFn;

	public GamePlayer(final Creature creature, final Deck deck,
					  ToIntFunction<Hand> chooseAttackCardFn)
	{
		checkNotNull(creature, "creature");
		checkNotNull(deck, "deck");
		checkNotNull(chooseAttackCardFn, "chooseAttackCardFn");
		checkArgument(deck.size() > RulesConstants.minDeckSize(),
					  "initial deck too small");
		this.creature = creature;
		this.deck = new Deck(deck);
		this.hand = new Hand();
		this.discards = new DiscardPile();
		this.chooseAttackCardFn = chooseAttackCardFn;
	}

	/**
	 * Draws a card from the deck into the hand. If the deck has at least one
	 * card, the top card is drawn and added to the hand. Otherwise, the cards
	 * in the discard pile are added to the empty deck and shuffled, then the
	 * top card is drawn and added to the hand.
	 * @return The card drawn.
	 */
	Card drawCard()
	{
		if(deck.isEmpty())
		{
			System.out.println("Deck is empty, reloading...");
			//Move all cards from the discards pile into the deck.
			while(discards.size() > 0)
			{
				deck.insertCardBottom(discards.remove(0));
			}
			deck.shuffle();
		}
		//Deck now has at least one card.
		final Card drawn = deck.drawCard();
		hand.add(drawn);
		System.out.printf("You drew %s%n", drawn.name());
		return drawn;
	}

	void shuffleDeck()
	{
		deck.shuffle();
	}
	
	//Chooses a card from the hand and discards it, returning that card.
	Card chooseAndPlayCard()
	{
		final Card chosen = hand.remove(chooseAttackCardFn.applyAsInt(hand));
		discards.discard(chosen);
		System.out.printf("%s plays %s!%n", creature.name(), chosen.name());
		return chosen;
	}
	
	int dealDamage(final int damage)
	{
		checkArgument(damage > 0, "damage must be positive");
		creature.dealDamage(damage);
		return creature.hp();
	}

	Creature creature()
	{
		return creature;
	}
}
