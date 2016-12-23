package meindratheal.collab.cavernsandcardsproto.creature;

import static com.google.common.base.Preconditions.*;

/**
 * Basic implementation of the {@link ModifiableCreature} interface.
 * @author Meindratheal
 */
public final class CreatureImpl implements ModifiableCreature
{
	private final String name;
	private int hp;
	private final int ac;

	public CreatureImpl(final String name, final int hp, final int ac)
	{
		this.name = checkNotNull(name, "name");
		this.hp = hp;
		this.ac = ac;
		checkArgument(hp > 0, "hp must be positive");
		checkArgument(ac >= 0, "ac must be non-negative");
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public int hp()
	{
		return hp;
	}

	@Override
	public int ac()
	{
		return ac;
	}

	@Override
	public int dealDamage(final int damage)
	{
		if(damage > hp)
		{
			hp = 0;
		}
		else
		{
			hp -= damage;
		}
		return hp();
	}
}
