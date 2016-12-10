package meindratheal.collab.cavernsandcardsproto;

import static com.google.common.base.Preconditions.*;

/**
 * An attack card. Has one extra attribute, the to-hit bonus.
 * @author Meindratheal
 */
public final class AttackCard extends Card
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

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 47 * hash + name().hashCode();
		hash = 47 * hash + toHitBonus();
		return hash;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if(obj instanceof AttackCard)
		{
			final AttackCard other = (AttackCard) obj;
			return this.name().equals(other.name())
					&& this.toHitBonus() == other.toHitBonus();
		}
		return false;
	}

}
