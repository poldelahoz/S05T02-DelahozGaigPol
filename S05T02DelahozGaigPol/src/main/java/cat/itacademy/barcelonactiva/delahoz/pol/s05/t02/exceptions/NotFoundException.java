package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions;

public class NotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
