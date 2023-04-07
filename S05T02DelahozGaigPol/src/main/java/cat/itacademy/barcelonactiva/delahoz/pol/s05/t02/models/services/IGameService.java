package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.List;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;

public interface IGameService {
	
	GameDTO newGame(long id) throws NotFoundException;
	
	List<GameDTO> getAllGames(long id) throws NotFoundException;

	void deleteAllGames(long id) throws NotFoundException;
}
