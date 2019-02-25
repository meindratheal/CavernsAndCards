package meindratheal.collab.cavernsandcardsproto.controller;

/**
 * Implementation of ScriptApi that only allows reading of the game state. Used
 * for the enter*Phase methods.
 * @author Meindratheal
 */
final class ReadOnlyApi extends BaseApi
{
	ReadOnlyApi(final TurnPhase phase, final GamePlayer self,
				final GamePlayer enemy)
	{
		super(phase, self, enemy);
	}

	@Override
	public void playCardFromHand(final int idx)
	{
		checkNotRestricted(false, "cannot play a card at this time");
	}

	@Override
	public void discardCardFromHand(int idx)
	{
		checkNotRestricted(false, "cannot discard a card at this time");
	}

}
