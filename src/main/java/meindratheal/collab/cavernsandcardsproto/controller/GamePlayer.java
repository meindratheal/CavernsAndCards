package meindratheal.collab.cavernsandcardsproto.controller;

import meindratheal.collab.cavernsandcardsproto.Card;
import meindratheal.collab.cavernsandcardsproto.RulesConstants;
import meindratheal.collab.cavernsandcardsproto.cardzones.DiscardPileImpl;
import meindratheal.collab.cavernsandcardsproto.cardzones.HandImpl;
import meindratheal.collab.cavernsandcardsproto.cardzones.ModifiableDeck;
import meindratheal.collab.cavernsandcardsproto.cardzones.ModifiableDiscardPile;
import meindratheal.collab.cavernsandcardsproto.cardzones.ModifiableHand;
import meindratheal.collab.cavernsandcardsproto.creature.ModifiableCreature;
import meindratheal.collab.cavernsandcardsproto.scripting.PlayerScript;

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
	private final ModifiableCreature creature;
	private final ModifiableDeck deck;
	private final ModifiableHand hand;
	private final ModifiableDiscardPile discards;
	private final PlayerScript script;

	public GamePlayer(final ModifiableCreature creature,
					  final ModifiableDeck deck,
					  final PlayerScript script)
	{
		checkNotNull(creature, "creature");
		checkNotNull(deck, "deck");
		checkNotNull(script, "script");
		checkArgument(deck.size() > RulesConstants.minDeckSize(),
					  "initial deck too small");
		this.creature = creature;
		this.deck = deck.copy();
		this.hand = new HandImpl();
		this.discards = new DiscardPileImpl();
		this.script = script;
	}

	/**
	 * Draws a card from the deck into the hand. If the deck has at least one
	 * card, the top card is drawn and added to the hand. Otherwise, the cards
	 * in the discard pile are added to the empty deck and shuffled, then the
	 * top card is drawn and added to the hand. Due to current game mechanics,
	 * it will always be possible to draw a card.
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
		return drawn;
	}

	ModifiableCreature creature()
	{
		return creature;
	}

	ModifiableDeck deck()
	{
		return deck;
	}

	ModifiableHand hand()
	{
		return hand;
	}

	ModifiableDiscardPile discards()
	{
		return discards;
	}

	PlayerScript script()
	{
		return script;
	}
}
