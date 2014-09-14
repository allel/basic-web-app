package tutorial.core.services.exceptions;

public class BlogExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 8220882772839848077L;

	public BlogExistsException() {
	}

	public BlogExistsException(String message) {
		super(message);
	}

	public BlogExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlogExistsException(Throwable cause) {
		super(cause);
	}
}