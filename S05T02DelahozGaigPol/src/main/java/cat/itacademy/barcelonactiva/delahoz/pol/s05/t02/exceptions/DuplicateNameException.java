package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions;

public class DuplicateNameException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DuplicateNameException(String errorMessage) {
		super(errorMessage);
	}
}