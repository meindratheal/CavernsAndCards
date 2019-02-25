package meindratheal.collab.cavernsandcardsproto.controller;

import java.util.function.IntConsumer;

import static com.google.common.base.Preconditions.*;

/**
 * ScriptApi implementation for the Battle Phase. Allows a single card to be
 * played.
 * @author Meindratheal
 */
final class BattlePhaseApi extends BaseApi
{
	/**
	 * Callback to invoke when the
	 * {@link #playCardFromHand(int) playCardFromHand} method is called.
	 */
	private final IntConsumer playCardCallback;
	private boolean cardPlayed = false;

	BattlePhaseApi(final GamePlayer self, final GamePlayer enemy,
				   final IntConsumer playCardCallback)
	{
		super(TurnPhase.BATTLE, self, enemy);
		this.playCardCallback = playCardCallback;
	}

	@Override
	public void playCardFromHand(final int idx)
	{
		checkNotRestricted(!cardPlayed, "card already played this turn");
		checkElementIndex(idx, getMyHand().size());
		//Set the flag before running the callback to prevent reentrancy.
		cardPlayed = true;
		playCardCallback.accept(idx);
	}

	@Override
	public void discardCardFromHand(int idx)
	{
		checkNotRestricted(false, "cannot discard a card at this time");
	}
}
