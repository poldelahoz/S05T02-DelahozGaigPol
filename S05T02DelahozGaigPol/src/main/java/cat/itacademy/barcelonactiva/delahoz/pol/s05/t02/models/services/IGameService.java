package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.List;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;

public interface IGameService {
	
	List<GameDTO> getAllGames(Integer id) throws NotFoundException;
	
	GameDTO newGame(Integer id) throws NotFoundException;

	void deleteAllGames(Integer id) throws NotFoundException;
}
