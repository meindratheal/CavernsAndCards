package meindratheal.collab.cavernsandcardsproto;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

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
			System.out.printf("Player %d's turn [%s, %d HP]%n",
							  turnPlayerNum,
							  turnPlayer.creature().name(),
							  turnPlayer.creature().hp());
			//Draw Phase.
			turnPlayer.drawCard();
			//Battle Phase.
			//Skip step 1. Hand size will always be 4 here with these rules.
			//Step 2: Choose card to use.
			final AttackCard card = (AttackCard) turnPlayer.chooseAndPlayCard();
			//Step 3. First, roll the d20 and add its value to the card's toHit.
			final int roll = rollD20();
			System.out.printf("You rolled a %d%n", roll);
			final int accuracy = roll + card.toHitBonus();
			//If this is >= the target's AC, do the card's damage.
			final GamePlayer target = players.get(turnPlayerNum == 0 ? 1 : 0);
			System.out.printf("Your accuracy is %d%nYour target's AC is %d%n", accuracy, target.creature().ac());
			if(accuracy > target.creature().ac())
			{
				final int damage = card.damage();
				System.out.printf("You did %d damage!%n", damage);
				final int remainingHp = target.dealDamage(damage);
				System.out.printf("%s has %d HP%n", target.creature().name(), remainingHp);
				if(remainingHp == 0)
				{
					//Game over!
					System.out.println("You win!");
					break;
				}
			}
			else
			{
				System.out.println("You missed!");
			}
			
			//End Phase. Can never reach the hand limit with these rules.
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
			player.shuffleDeck();
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
}
