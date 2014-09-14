package tutorial.core.services.exceptions;

public class AccountExistsException  extends RuntimeException {

	private static final long serialVersionUID = 4658445154353483521L;

	public AccountExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountExistsException(String message) {
		super(message);
	}

	public AccountExistsException() {
		super();
	}

}
