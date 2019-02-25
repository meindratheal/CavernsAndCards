package meindratheal.collab.cavernsandcardsproto.demo;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import meindratheal.collab.cavernsandcardsproto.AttackCard;
import meindratheal.collab.cavernsandcardsproto.Card;
import meindratheal.collab.cavernsandcardsproto.cardzones.ReadableHand;
import meindratheal.collab.cavernsandcardsproto.controller.GameController;
import meindratheal.collab.cavernsandcardsproto.controller.GamePlayer;
import meindratheal.collab.cavernsandcardsproto.controller.ScriptApi;
import meindratheal.collab.cavernsandcardsproto.creature.CreatureImpl;
import meindratheal.collab.cavernsandcardsproto.creature.ModifiableCreature;
import meindratheal.collab.cavernsandcardsproto.scripting.PlayerScript;

/**
 *
 * @author Meindratheal
 */
public final class TestingMain
{
	public static void main(String[] args)
	{
		final ModifiableCreature c1 = new CreatureImpl("Tank", 100, 13);
		final ModifiableCreature c2 = new CreatureImpl("Rogue", 70, 17);
		final GamePlayer playerOne = new GamePlayer(c1,
													TestingCards.createDeck(),
													new HumanPlayerScript());
		final GamePlayer playerTwo = new GamePlayer(c2,
													TestingCards.createDeck(),
													new AiPlayerScriptExpectedDamage());
		final GameController control = new GameController(playerOne, playerTwo);
		final int winner = control.play();
		System.out.printf("Player %d wins%n", winner);
	}

	private static int chooseCard(final ReadableHand hand)
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

	private static final class HumanPlayerScript implements PlayerScript
	{
		@Override
		public void onCardDrawn(final ScriptApi api, final Card card)
		{
			System.out.printf("You drew %s%n", card.name());
		}

		@Override
		public void enterDrawPhase(final ScriptApi api)
		{
			System.out.println("Entering Draw Phase");
		}

		@Override
		public void enterBattlePhase(final ScriptApi api)
		{
			System.out.println("Entering Battle Phase");
		}

		@Override
		public void enterEndPhase(ScriptApi api)
		{
			System.out.println("Entering End Phase");
		}

		@Override
		public void onReceivePriority(ScriptApi api)
		{
			System.out.println("Choose a card to play");
			api.playCardFromHand(chooseCard(api.getMyHand()));
		}

		@Override
		public void discardToHandLimit(final ScriptApi api, final int handLimit)
		{
			System.out.println("Over hand limit! Choose a card to discard.");
			api.discardCardFromHand(chooseCard(api.getMyHand()));
		}
	}
	
	//Plays no cards.
	private static final class AiPlayerScriptNull implements PlayerScript{
		private final Random rng = new Random();

		@Override
		public void onCardDrawn(final ScriptApi api, final Card card)
		{
		}

		@Override
		public void enterDrawPhase(final ScriptApi api)
		{
			System.out.println("deh");
		}

		@Override
		public void enterBattlePhase(final ScriptApi api)
		{
			System.out.println("beh");
		}

		@Override
		public void enterEndPhase(final ScriptApi api)
		{
			System.out.println("eeh");
		}

		@Override
		public void onReceivePriority(final ScriptApi api)
		{
			System.out.println("peh");
		}

		@Override
		public void discardToHandLimit(final ScriptApi api, int handLimit)
		{
			//The one we actually have to do.
			System.out.println("oops");
			api.discardCardFromHand(rng.nextInt(api.getMyHand().size()));
		}
	}


	//Picks a card to play randomly from its hand.
	private static final class AiPlayerScriptRandom implements PlayerScript
	{
		private final Random rng = new Random();

		boolean playedCardThisTurn = false;

		@Override
		public void onCardDrawn(final ScriptApi api, final Card card)
		{
			System.out.println("AI drew a card");
		}

		@Override
		public void enterDrawPhase(final ScriptApi api)
		{
		}

		@Override
		public void enterBattlePhase(final ScriptApi api)
		{
			playedCardThisTurn = false;
		}

		@Override
		public void enterEndPhase(final ScriptApi api)
		{
		}

		@Override
		public void onReceivePriority(final ScriptApi api)
		{
			//Play a card randomly.
			if(!playedCardThisTurn)
			{
				api.playCardFromHand(rng.nextInt(api.getMyHand().size()));
				playedCardThisTurn = true;
			}
		}

		@Override
		public void discardToHandLimit(final ScriptApi api, final int handLimit)
		{
			//Discard one randomly, this will be called repeatedly until we
			//discard enough.
			api.discardCardFromHand(rng.nextInt(api.getMyHand().size()));
		}
	}

	//Picks the card with the highest expected damage (dam * hitChance).
	private static final class AiPlayerScriptExpectedDamage implements
			PlayerScript
	{
		boolean playedCardThisTurn = false;

		@Override
		public void onCardDrawn(final ScriptApi api, final Card card)
		{
			System.out.println("AI drew a card");
		}

		@Override
		public void enterDrawPhase(final ScriptApi api)
		{
		}

		@Override
		public void enterBattlePhase(final ScriptApi api)
		{
			playedCardThisTurn = false;
		}

		@Override
		public void enterEndPhase(final ScriptApi api)
		{
		}

		@Override
		public void onReceivePriority(ScriptApi api)
		{
			if(!playedCardThisTurn)
			{
				System.out.println("AI hand:");
				final ReadableHand hand = api.getMyHand();
				final int targetAc = api.getEnemyCreature().ac();

				int bestIdx = Integer.MIN_VALUE;
				double bestHitChance = Double.MIN_VALUE;
				double bestExpDam = Double.MIN_VALUE;

				for(int cardIdx = 0; cardIdx < hand.size(); cardIdx++)
				{
					final AttackCard card = (AttackCard) hand.peek(cardIdx);
					final double hitChance = clamp(
							(21 + card.toHitBonus() - targetAc) / 20.0, 0.0, 1.0);
					final double expDam = hitChance * card.damage();
					System.out.printf("%s hitChance=%f expectedDamage=%f", card
									  .name(), hitChance, expDam);
					if(expDam > bestExpDam)
					{
						//Definitely better.
						bestIdx = cardIdx;
						bestHitChance = hitChance;
						bestExpDam = expDam;
						System.out.printf(" <<< new favourite");
					}
					else if(expDam == bestExpDam)
					{
						//Equal expected damage, go for the more consistent one.
						if(hitChance > bestHitChance)
						{
							bestIdx = cardIdx;
							bestHitChance = hitChance;
							bestExpDam = expDam;
							System.out.printf(" <<< new favourite");
						}
					}
					System.out.println();
				}

				api.playCardFromHand(bestIdx);
			}
		}

		@Override
		public void discardToHandLimit(ScriptApi api, int handLimit)
		{
			//Discard the weakest.System.out.println("AI hand:");
			final ReadableHand hand = api.getMyHand();
			final int targetAc = api.getEnemyCreature().ac();

			int worstIdx = Integer.MIN_VALUE;
			double worstHitChance = Double.MIN_VALUE;
			double worstExpDam = Double.MIN_VALUE;

			for(int cardIdx = 0; cardIdx < hand.size(); cardIdx++)
			{
				final AttackCard card = (AttackCard) hand.peek(cardIdx);
				final double hitChance = clamp(
						(21 + card.toHitBonus() - targetAc) / 20.0, 0.0, 1.0);
				final double expDam = hitChance * card.damage();
				System.out.printf("%s hitChance=%f expectedDamage=%f", card
								  .name(), hitChance, expDam);
				if(expDam < worstExpDam)
				{
					//Definitely worse.
					worstIdx = cardIdx;
					worstHitChance = hitChance;
					worstExpDam = expDam;
					System.out.printf(" <<< new favourite");
				}
				else if(expDam == worstExpDam)
				{
					//Equal expected damage, ditch the less consistent one.
					if(hitChance < worstHitChance)
					{
						worstIdx = cardIdx;
						worstHitChance = hitChance;
						worstExpDam = expDam;
						System.out.printf(" <<< new favourite");
					}
				}
				System.out.println();
			}

			api.discardCardFromHand(worstIdx);
		}

		private static double clamp(final double val, final double min,
									final double max)
		{
			return Math.max(min, Math.min(max, val));
		}
	}
}
