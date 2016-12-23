package meindratheal.collab.cavernsandcardsproto.creature;

/**
 * Represents a Creature. Creatures are the in-game avatars of the players, and
 * have a name, hit points, and AC. This is the read-write interface.
 * @author Meindratheal
 * @see ReadableCreature
 */
public interface ModifiableCreature extends ReadableCreature
{
	/**
	 * Deals the specified amount of damage to this Creature, and returns the
	 * Creature's remaining HP. If the damage is greater than the Creature's
	 * remaining HP, the Creature is reduced to 0 HP.
	 * @param damage The amount of damage to deal. Must be positive.
	 * @return The Creature's remaining HP.
	 */
	int dealDamage(int damage);
}
