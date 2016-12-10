package meindratheal.collab.cavernsandcardsproto;

import static com.google.common.base.Preconditions.*;

/**
 * Base class of all card types.
 * @author Meindratheal
 */
public abstract class Card
{
	private final String name;

	public Card(final String name)
	{
		this.name = checkNotNull(name, "name");
	}
	
	public final String name()
	{
		return name;
	}
}
