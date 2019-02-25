package meindratheal.collab.cavernsandcardsproto.scripting;

import meindratheal.collab.cavernsandcardsproto.Card;
import meindratheal.collab.cavernsandcardsproto.controller.ScriptApi;

/**
 * Contains the scripting interface for players (human or computer) to interact
 * with the game engine.
 * @author Meindratheal
 */
public interface PlayerScript
{
	void onCardDrawn(ScriptApi api, Card card);
	void enterDrawPhase(ScriptApi api);
	void enterBattlePhase(ScriptApi api);
	void enterEndPhase(ScriptApi api);
	
	void onReceivePriority(ScriptApi api);
	
	void discardToHandLimit(ScriptApi api, int handLimit);
}
