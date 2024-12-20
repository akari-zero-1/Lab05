package hust.soict.hedspi.aims.exception;

public class PlayerException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public PlayerException() {
		
	}

	public PlayerException(String message) {
		super(message);
		
	}

	public PlayerException(Throwable cause) {
		super(cause);
		
	}

	public PlayerException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}