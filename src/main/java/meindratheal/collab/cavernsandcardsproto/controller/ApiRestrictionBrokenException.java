package meindratheal.collab.cavernsandcardsproto.controller;

/**
 * Thrown to indicate a restriction condition in a {@link ScriptApi} call has
 * been broken.
 * @author Meindratheal
 */
public final class ApiRestrictionBrokenException extends IllegalStateException
{
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of {@code RestrictionBrokenException} without
	 * detail message.
	 */
	public ApiRestrictionBrokenException()
	{
		super();
	}

	/**
	 * Constructs an instance of {@code RestrictionBrokenException} with the
	 * specified detail message.
	 * @param msg The detail message.
	 */
	public ApiRestrictionBrokenException(final String msg)
	{
		super(msg);
	}
}
