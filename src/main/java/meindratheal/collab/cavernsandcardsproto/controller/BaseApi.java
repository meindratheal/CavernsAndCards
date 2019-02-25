package meindratheal.collab.cavernsandcardsproto.controller;

import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableDeck;
import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableDiscardPile;
import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableHand;
import meindratheal.collab.cavernsandcardsproto.creature.ReadableCreature;

/**
 * Basic ScriptApi implementation that implements the methods for reading the
 * game state. Other ScriptApi implementations can inherit from this class to
 * get this base functionality and implement the other methods themselves.
 * @author Meindratheal
 */
abstract class BaseApi extends ScriptApi
{
	private final TurnPhase phase;
	private final GamePlayer self;
	private final GamePlayer enemy;

	BaseApi(final TurnPhase phase, final GamePlayer self, final GamePlayer enemy)
	{
		this.phase = phase;
		this.self = self;
		this.enemy = enemy;
	}
	
	//Accessors. Here so subclasses can access the Modifiable* data if needed.
	TurnPhase phase()
	{
		return phase;
	}
	GamePlayer self()
	{
		return self;
	}
	GamePlayer enemy()
	{
		return enemy;
	}

	@Override
	public TurnPhase getPhase()
	{
		return phase;
	}

	@Override
	public ReadableCreature getMyCreature()
	{
		return self.creature();
	}

	@Override
	public ReadableCreature getEnemyCreature()
	{
		return enemy.creature();
	}

	@Override
	public ReadableHand getMyHand()
	{
		return self.hand();
	}

	@Override
	public ReadableHand getEnemyHand()
	{
		return enemy.hand();
	}

	@Override
	public ReadableDeck getMyDeck()
	{
		return self.deck();
	}

	@Override
	public ReadableDeck getEnemyDeck()
	{
		return enemy.deck();
	}

	@Override
	public ReadableDiscardPile getMyDiscards()
	{
		return self.discards();
	}

	@Override
	public ReadableDiscardPile getEnemyDiscards()
	{
		return enemy.discards();
	}
}
