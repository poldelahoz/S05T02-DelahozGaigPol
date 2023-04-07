package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Game;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.SequenceDao;

@Service
public class GameService implements IGameService{
	
	private static final String GAME_SEQ_KEY = "game";

	@Autowired
  	PlayerRepository playerRepository;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public GameDTO newGame(long id) throws NotFoundException {
		Player _player = playerRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Not found Player with id = " + id));
		
		Game game = new Game();
		game.setId(sequenceDao.getNextSequenceId(GAME_SEQ_KEY));
		game.setCreatedAt(new Date());
		_player.addGame(game);
		playerRepository.save(_player);
		GameDTO gameDto = new GameDTO(game);
		return gameDto;
	}
	
	@Override
	public List<GameDTO> getAllGames(long id) throws NotFoundException {
		Player _player = playerRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Not found Player with id = " + id));
		
		List<GameDTO> gamesDtos = _player.getGames().stream().map(c -> new GameDTO(c)).collect(Collectors.toList());
		
		return gamesDtos;
	}
	
	@Override
	public void deleteAllGames(long id) throws NotFoundException {
		Player _player = playerRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Not found Player with id = " + id));
		_player.setGames(new ArrayList<Game>());
		playerRepository.save(_player);
	}
}
