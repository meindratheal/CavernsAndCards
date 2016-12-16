package meindratheal.collab.cavernsandcardsproto;

import static com.google.common.base.Preconditions.*;

/**
 * An attack card. Has two extra attributes, the to-hit bonus and the damage.
 * @author Meindratheal
 */
public final class AttackCard extends Card
{
	private final int toHitBonus;
	private final int damage;
	
	public AttackCard(final String name, final int toHitBonus, final int damage)
	{
		super(name);
		checkArgument(toHitBonus >= 0, "toHitBonus must be non-negative");
		checkArgument(damage > 0, "damage must be positive");
		this.toHitBonus = toHitBonus;
		this.damage = damage;
	}

	public final int toHitBonus()
	{
		return toHitBonus;
	}
	
	public final int damage()
	{
		return damage;
	}

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 47 * hash + name().hashCode();
		hash = 47 * hash + toHitBonus();
		hash = 47 * hash + damage();
		return hash;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if(obj instanceof AttackCard)
		{
			final AttackCard other = (AttackCard) obj;
			return this.name().equals(other.name())
					&& this.toHitBonus() == other.toHitBonus()
					&& this.damage() == other.damage();
		}
		return false;
	}

}
