package bioresultats.core.services.exceptions;

public class AccountDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = -5799915854822217611L;

	public AccountDoesNotExistsException(Throwable cause) {
		super(cause);
	}

	public AccountDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountDoesNotExistsException(String message) {
		super(message);
	}

	public AccountDoesNotExistsException() {
	}

}
