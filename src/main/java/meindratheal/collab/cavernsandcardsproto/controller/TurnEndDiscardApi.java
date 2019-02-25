package meindratheal.collab.cavernsandcardsproto.controller;

import static com.google.common.base.Preconditions.*;

/**
 * Implementation of ScriptApi that only allows discarding cards, used during
 * the discard-to-hand-limit part of the End Phase.
 * @author Meindratheal
 */
public class TurnEndDiscardApi extends BaseApi
{
	private final int handLimit;

	public TurnEndDiscardApi(final GamePlayer self, final GamePlayer enemy,
							 final int handLimit)
	{
		super(TurnPhase.END, self, enemy);
		this.handLimit = handLimit;
	}

	@Override
	public void playCardFromHand(final int idx)
	{
		checkNotRestricted(false, "cannot play a card at this time");
	}

	@Override
	public void discardCardFromHand(final int idx)
	{
		checkNotRestricted(getMyHand().size() > handLimit,
						   "cannot discard further below hand limit");
		checkElementIndex(idx, getMyHand().size());
		self().discards().discard(self().hand().remove(idx));
	}

}
