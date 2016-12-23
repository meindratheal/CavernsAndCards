package meindratheal.collab.cavernsandcardsproto.creature;

/**
 * Represents a Creature. Creatures are the in-game avatars of the players, and
 * have a name, hit points, and AC. This is the read-only interface.
 * @author Meindratheal
 * @see ModifiableCreature
 */
public interface ReadableCreature
{
	String name();
	int hp();
	int ac();
}
