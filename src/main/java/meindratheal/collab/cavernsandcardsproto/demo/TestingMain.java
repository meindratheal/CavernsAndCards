package meindratheal.collab.cavernsandcardsproto.demo;

import java.util.InputMismatchException;
import java.util.Scanner;
import meindratheal.collab.cavernsandcardsproto.AttackCard;
import meindratheal.collab.cavernsandcardsproto.Creature;
import meindratheal.collab.cavernsandcardsproto.GameController;
import meindratheal.collab.cavernsandcardsproto.GamePlayer;
import meindratheal.collab.cavernsandcardsproto.Hand;

/**
 *
 * @author Meindratheal
 */
public final class TestingMain
{
	public static void main(String[] args)
	{
		final Creature c1 = new Creature("Tank", 100, 13);
		final Creature c2 = new Creature("Rogue", 70, 17);
		final GamePlayer playerOne = new GamePlayer(c1,
													TestingCards.createDeck(),
													TestingMain::chooseCard);
		final GamePlayer playerTwo = new GamePlayer(c2,
													TestingCards.createDeck(),
													TestingMain::chooseCard);
		final GameController control = new GameController(playerOne, playerTwo);
		final int winner = control.play();
		System.out.printf("Player %d wins%n", winner);
	}

	private static int chooseCard(final Hand hand)
	{
		//Note that users deal with the range [1, size].
		System.out.println("Your hand:");
		for(int i = 0; i < hand.size(); i++)
		{
			final AttackCard card = (AttackCard) hand.peek(i);
			System.out.printf("%d) \"%s\" [%d toHit, %d damage]%n",
							  i + 1,
							  card.name(),
							  card.toHitBonus(),
							  card.damage());
		}
		final int chosen = getIntFromConsole(1, hand.size()) - 1;
		return chosen;
	}

	private static int getIntFromConsole(final int minValid, final int maxValid)
	{
		final Scanner scanner = new Scanner(System.in);
		System.out.printf("Enter an integer from %d to %d%n", minValid,
						  maxValid);
		while(true)
		{
			try
			{
				final int val = scanner.nextInt();
				if(val < minValid || val > maxValid)
				{
					System.out.println("Out of range.");
					//Read this line, so the bad input doesn't get reused.
					scanner.nextLine();
				}
				else
				{
					return val;
				}
			}
			catch(final InputMismatchException ex)
			{
				System.out.println("Not an int");
				//Read this line, so the bad input doesn't get reused.
				scanner.nextLine();
			}
		}
	}
}
