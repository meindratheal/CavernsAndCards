package meindratheal.collab.cavernsandcardsproto.controller;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;
import meindratheal.collab.cavernsandcardsproto.AttackCard;
import meindratheal.collab.cavernsandcardsproto.RulesConstants;

import static com.google.common.base.Preconditions.*;

/**
 * The main controller of a game.
 * <p>
 * Based on the rules of 2016/12/16.
 * @author Meindratheal
 */
public final class GameController
{
	private final List<GamePlayer> players;
	private final Random rng = new Random();

	public GameController(final GamePlayer playerOne, final GamePlayer playerTwo)
	{
		this.players = Lists.newArrayList(checkNotNull(playerOne, "playerOne"),
										  checkNotNull(playerTwo, "playerTwo"));
	}

	//Returns 0 if player 1 wins, 1 if player 2 wins.
	public int play()
	{
		runPreGameSetup();
		//Step 2 of the Gameplay list: Randomly determine who starts.
		int turnPlayerNum = rng.nextInt(players.size());
		while(true)
		{
			final GamePlayer turnPlayer = players.get(turnPlayerNum);
			final GamePlayer enemyPlayer = players.get(
					turnPlayerNum == 0 ? 1 : 0);
			System.out.printf("Player %d's turn [%s, %d HP]%n",
							  turnPlayerNum,
							  turnPlayer.creature().name(),
							  turnPlayer.creature().hp());
			//Draw Phase.
			turnPlayer.script().enterDrawPhase(
					new ReadOnlyApi(TurnPhase.DRAW, turnPlayer, enemyPlayer));
			turnPlayer.script().onCardDrawn(
					new ReadOnlyApi(TurnPhase.DRAW, turnPlayer, enemyPlayer),
					turnPlayer.drawCard());
			//Battle Phase.
			turnPlayer.script().enterBattlePhase(
					new ReadOnlyApi(TurnPhase.BATTLE, turnPlayer, enemyPlayer));
			//Set up for step 1a: If a card is played, this callback is invoked.
			final IntConsumer playCardCallback = createOnPlayCardCallback(
					turnPlayer, enemyPlayer);
			//Step 1: Play either 0 or 1 cards.
			turnPlayer.script().onReceivePriority(
					new BattlePhaseApi(turnPlayer, enemyPlayer, playCardCallback));
			//Step 2: Check enemy HP.
			if(enemyPlayer.creature().hp() == 0)
			{
				//Game over!
				System.out.println("You win!");
				break;
			}
			//End Phase.
			turnPlayer.script().enterEndPhase(
					new ReadOnlyApi(TurnPhase.END, turnPlayer, enemyPlayer));
			//Step 1: If over hand limit, discard cards.
			//Do this in a loop in case the script doesn't discard enough.
			final int handLimit = RulesConstants.maxHandSize();
			while(turnPlayer.hand().size() > handLimit)
			{
				turnPlayer.script().discardToHandLimit(
						new TurnEndDiscardApi(turnPlayer, enemyPlayer, handLimit),
						handLimit);
			}
			//Next player's turn.
			turnPlayerNum = (turnPlayerNum + 1) % players.size();
		}
		return turnPlayerNum;
	}

	/**
	 * Performs steps 1 and 3 of the Gameplay list. Step 2 is covered elsewhere.
	 */
	private void runPreGameSetup()
	{
		//Step 1: Shuffle all decks.
		for(GamePlayer player : players)
		{
			player.deck().shuffle();
		}
		//Step 3: Initial draws.
		for(GamePlayer player : players)
		{
			for(int i = 0; i < RulesConstants.initialHandSize(); i++)
			{
				player.drawCard();
			}
		}
	}

	private int rollD20()
	{
		return rng.nextInt(20) + 1;
	}

	private IntConsumer createOnPlayCardCallback(final GamePlayer turnPlayer,
												 final GamePlayer enemyPlayer)
	{
		return idx ->
		{
			//Remove the card from the hand and add to discards.
			final AttackCard card = (AttackCard) turnPlayer.hand()
					.remove(idx);
			turnPlayer.discards().discard(card);
			//Step 3. First, roll the d20 and add its value to the card's toHit.
			final int roll = rollD20();
			System.out.printf("You rolled a %d%n", roll);
			final int accuracy = roll + card.toHitBonus();
			//If this is >= the target's AC, do the card's damage.
			System.out.printf(
					"Your accuracy is %d%nYour target's AC is %d%n",
					accuracy, enemyPlayer.creature().ac());
			if(accuracy > enemyPlayer.creature().ac())
			{
				final int damage = card.damage();
				System.out.printf("You did %d damage!%n", damage);
				final int remainingHp = enemyPlayer.creature().dealDamage(
						damage);
				System.out.printf("%s has %d HP%n", enemyPlayer.creature()
								  .name(),
								  remainingHp);
			}
			else
			{
				System.out.println("You missed!");
			}
		};
	}
}
