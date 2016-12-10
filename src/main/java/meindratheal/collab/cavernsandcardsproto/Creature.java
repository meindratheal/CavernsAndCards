package meindratheal.collab.cavernsandcardsproto;

import static com.google.common.base.Preconditions.*;

/**
 * A Creature. Creatures are the in-game avatars of the players, and have a name
 * and hit points.
 * @author Meindratheal
 */
public class Creature
{
	private final String name;
	private int hp;

	public Creature(final String name, final int hp)
	{
		this.name = checkNotNull(name, "name");
		this.hp = hp;
		checkArgument(hp > 0, "hp must be positive");
	}

	public String name()
	{
		return name;
	}

	public int hp()
	{
		return hp;
	}

	public void dealDamage(final int damage)
	{
		if(damage > hp)
		{
			hp = 0;
		}
		else
		{
			hp -= damage;
		}
	}
}
