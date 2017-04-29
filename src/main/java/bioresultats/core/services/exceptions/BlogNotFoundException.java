package bioresultats.core.services.exceptions;

public class BlogNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -7933194939953907029L;

	public BlogNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlogNotFoundException(String message) {
		super(message);
	}

	public BlogNotFoundException() {
	}
}
