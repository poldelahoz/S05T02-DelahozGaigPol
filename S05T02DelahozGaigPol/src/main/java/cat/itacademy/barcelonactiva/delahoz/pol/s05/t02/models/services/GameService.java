package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Game;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.GameRepository;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.PlayerRepository;

@Service
public class GameService implements IGameService{

	@Autowired
  	GameRepository gameRepository;

	@Autowired
  	PlayerRepository playerRepository;
	
	@Override
	public List<GameDTO> getAllGames(Integer id) throws NotFoundException {
		if(!playerRepository.existsById(id)) {
			throw new NotFoundException("Not found Player with id = " + id);
		}
		List<GameDTO> gamesDtos = gameRepository.findByPlayerId(id).stream().map(c -> new GameDTO(c)).collect(Collectors.toList());
		return gamesDtos;
	}
	
	@Override
	public GameDTO newGame(Integer id) throws NotFoundException {
		Player _player = playerRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Not found Player with id = " + id));
		Game game = new Game();
		game.setPlayer(_player);
		GameDTO gameDto = new GameDTO(gameRepository.save(game));
		return gameDto;
	}
	
	@Override
	public void deleteAllGames(Integer id) throws NotFoundException {
		if(!playerRepository.existsById(id)) {
			throw new NotFoundException("Not found Player with id = " + id);
		}
		gameRepository.deleteByPlayerId(id);
	}
}
