package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions;

public class NoPlayersRegisteredException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoPlayersRegisteredException(String errorMessage) {
		super(errorMessage);
	}
}

