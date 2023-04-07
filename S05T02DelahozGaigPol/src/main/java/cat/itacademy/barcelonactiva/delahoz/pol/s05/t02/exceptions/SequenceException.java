package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions;

public class SequenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errorMessage;

	public SequenceException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}