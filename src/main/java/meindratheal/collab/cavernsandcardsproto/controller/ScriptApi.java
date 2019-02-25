package meindratheal.collab.cavernsandcardsproto.controller;

import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableDeck;
import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableDiscardPile;
import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableHand;
import meindratheal.collab.cavernsandcardsproto.creature.ReadableCreature;

/**
 * Contains the API for
 * {@link meindratheal.collab.cavernsandcardsproto.scripting.PlayerScript}
 * implementations to use.
 * <p>
 * Not every method can be called at any time. Methods that simply return
 * information are typically unrestricted, but other methods may only be
 * callable during a certain game phase, or can only be called once per phase.
 * All methods with such a restriction clearly document the conditions they can
 * be used in. Breaking these restrictions will cause a
 * {@link ApiRestrictionBrokenException} to be thrown.
 * <p>
 * Unless otherwise specified, all changes to game state (including the cards in
 * each card zone and the status of each Creature) will be reflected
 * immediately.
 * @author Meindratheal
 */
public abstract class ScriptApi
{
	/**
	 * Package-private constructor, so implementations can only be made in this
	 * package.
	 */
	ScriptApi()
	{
	}

	/**
	 * Gets the current game phase.
	 * @return The current game phase.
	 */
	public abstract TurnPhase getPhase();

	/**
	 * Gets a read-only reference to the Creature belonging to the script owner.
	 * This is not necessarily the turn player.
	 * @return The script owner's Creature.
	 */
	public abstract ReadableCreature getMyCreature();

	/**
	 * Gets a read-only reference to the Creature belonging to the opponent.
	 * @return The opponent's Creature.
	 */
	public abstract ReadableCreature getEnemyCreature();

	/**
	 * Gets a read-only reference to the hand belonging to the script owner.
	 * @return The script owner's hand.
	 */
	public abstract ReadableHand getMyHand();

	/**
	 * Gets a read-only reference to the hand belonging to the opponent.
	 * @return The opponent's hand.
	 */
	public abstract ReadableHand getEnemyHand();

	/**
	 * Gets a read-only reference to the deck belonging to the script owner.
	 * @return The script owner's deck.
	 */
	public abstract ReadableDeck getMyDeck();

	/**
	 * Gets a read-only reference to the deck belonging to the opponent.
	 * @return The opponent's deck.
	 */
	public abstract ReadableDeck getEnemyDeck();

	/**
	 * Gets a read-only reference to the discard pile belonging to the script
	 * owner.
	 * @return The script owner's discard pile.
	 */
	public abstract ReadableDiscardPile getMyDiscards();

	/**
	 * Gets a read-only reference to the discard pile belonging to the opponent.
	 * @return The opponent's discard pile.
	 */
	public abstract ReadableDiscardPile getEnemyDiscards();

	/**
	 * Plays a card from the script owner's hand. This will typically remove the
	 * card from the player's hand, and add it to the top of their discard pile.
	 * <p>
	 * Restrictions:
	 * <ul><li>This can only be called once per turn, during the Battle
	 * Phase.</li></ul>
	 * @param idx The index of the card to play in the script owner's hand.
	 * @throws ApiRestrictionBrokenException If called outside of its
	 * restrictions.
	 * @throws IndexOutOfBoundsException If {@code idx} doesn't correspond to a
	 * valid index into the script owner's hand.
	 */
	public abstract void playCardFromHand(final int idx);

	/**
	 * Discards a card from the script owner's hand. Cards may not be discarded
	 * arbitrarily, only when a card effect or game rules say to (for example,
	 * if the turn player is over the hand limit at the end of their turn).
	 * Restrictions:
	 * <ul><li>This can only be called during from within
	 * {@link meindratheal.collab.cavernsandcardsproto.scripting.PlayerScript#discardToHandLimit(ScriptApi, int)}
	 * and only while the number of cards in the player's hand is greater than
	 * the hand limit.</li></ul>
	 * @param idx The index of the card to discard in the script owner's hand.
	 * @throws ApiRestrictionBrokenException If called outside of its
	 * restrictions.
	 * @throws IndexOutOfBoundsException If {@code idx} doesn't correspond to a
	 * valid index into the script owner's hand.
	 */
	public abstract void discardCardFromHand(final int idx);

	/**
	 * Utility precondition test method, akin to those in Guava. If the given
	 * condition is false, an {@link ApiRestrictionBrokenException} is thrown.
	 * @param condition The condition to test.
	 * @param errorMessage The message to pass to the exception constructor if
	 * the condition is false.
	 * @throws ApiRestrictionBrokenException If the condition is false.
	 */
	protected static void checkNotRestricted(final boolean condition,
											 final String errorMessage)
	{
		if(!condition)
		{
			throw new ApiRestrictionBrokenException(errorMessage);
		}
	}
}
