package game;

public class IllegalParametersException extends RuntimeException {
	private IllegalParametersException() { }
	public IllegalParametersException(String message) {
		super(message);
	}

}
