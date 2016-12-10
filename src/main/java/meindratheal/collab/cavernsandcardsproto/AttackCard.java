package meindratheal.collab.cavernsandcardsproto;

import static com.google.common.base.Preconditions.*;

/**
 * An attack card. Has one extra attribute, the to-hit bonus.
 * @author Meindratheal
 */
public class AttackCard extends Card
{
	private final int toHitBonus;
	
	public AttackCard(final String name, final int toHitBonus)
	{
		super(name);
		this.toHitBonus = toHitBonus;
		checkArgument(toHitBonus >= 0, "toHitBonus must be non-negative");
	}

	public final int toHitBonus()
	{
		return toHitBonus;
	}

}
