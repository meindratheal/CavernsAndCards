package meindratheal.collab.cavernsandcardsproto;

/**
 * Contains constants defined by the rules.
 * @author Meindratheal
 */
public final class RulesConstants
{
	/**
	 * Suppress default constructor.
	 */
	private RulesConstants()
	{
		throw new AssertionError();
	}

	/**
	 * The minimum number of cards required in a deck when starting a game.
	 * @return The minimum deck size
	 */
	public static int minDeckSize()
	{
		return 10;
	}

	/**
	 * The number of cards each player draws from their deck when starting a
	 * game.
	 * @return The initial hand size.
	 */
	public static int initialHandSize()
	{
		return 3;
	}

	/**
	 * The maximum number of cards allowed in a player's hand at the end of
	 * their turn. This limit may be exceeded at any other point, but if a
	 * player's hand has more cards than this value at the end of their turn
	 * then they must discard cards until they fall below this limit.
	 * @return The maximum hand size allowed at the end of the turn.
	 */
	public static int maxHandSize()
	{
		return 4;
	}
}
