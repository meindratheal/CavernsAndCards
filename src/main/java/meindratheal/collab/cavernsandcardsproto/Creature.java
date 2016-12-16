package meindratheal.collab.cavernsandcardsproto;

import static com.google.common.base.Preconditions.*;

/**
 * A Creature. Creatures are the in-game avatars of the players, and have a
 * name, hit points, and AC.
 * @author Meindratheal
 */
public class Creature
{
	private final String name;
	private int hp;
	private final int ac;

	public Creature(final String name, final int hp, final int ac)
	{
		this.name = checkNotNull(name, "name");
		this.hp = hp;
		this.ac = ac;
		checkArgument(hp > 0, "hp must be positive");
		checkArgument(ac >= 0, "ac must be non-negative");
	}

	public String name()
	{
		return name;
	}

	public int hp()
	{
		return hp;
	}

	public int ac()
	{
		return ac;
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
