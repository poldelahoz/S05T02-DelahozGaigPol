package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.List;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.DuplicateNameException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NoPlayersRegisteredException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.SequenceException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.PlayerDTO;

public interface IPlayerService {

	List<PlayerDTO> getAllPlayers() throws NoPlayersRegisteredException;
	
	double getTotalAverageWinRate()  throws NoPlayersRegisteredException;
	
	PlayerDTO getLoserPlayer() throws NotFoundException, NoPlayersRegisteredException;	
	
	PlayerDTO getWinnerPlayer() throws NotFoundException, NoPlayersRegisteredException;	
	
	PlayerDTO addPlayer(Player player) throws SequenceException, DuplicateNameException;

	PlayerDTO updatePlayer(long id, Player player) throws NotFoundException, DuplicateNameException;
}
