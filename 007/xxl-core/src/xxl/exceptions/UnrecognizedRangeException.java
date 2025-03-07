package xxl.exceptions;

import java.io.Serial;

/**
 * Exception for unknown import file entries.
 */
public class UnrecognizedRangeException extends Exception {

	@Serial
	private static final long serialVersionUID = 202308312359L;

	/** Unrecognized entry specification. */
	private String _entrySpecification;

	/**
	 * @param entrySpecification
	 */
	public UnrecognizedRangeException(String entrySpecification) {
		_entrySpecification = entrySpecification;
	}

	/**
	 * @param entrySpecification
	 * @param cause
	 */
	public UnrecognizedRangeException(String entrySpecification, Exception cause) {
		super(cause);
		_entrySpecification = entrySpecification;
	}

}
