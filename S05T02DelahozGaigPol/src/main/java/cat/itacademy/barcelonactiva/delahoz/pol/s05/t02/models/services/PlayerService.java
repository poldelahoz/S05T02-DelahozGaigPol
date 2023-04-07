package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.DuplicateNameException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NoPlayersRegisteredException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.SequenceException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.SequenceDao;

@Service
public class PlayerService implements IPlayerService{
	
	private static final String PLAYER_SEQ_KEY = "player";
	
	@Autowired
  	PlayerRepository playerRepository;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public List<PlayerDTO> getAllPlayers() throws NoPlayersRegisteredException {
		List<Player> players = playerRepository.findAll();
		
		if (players.isEmpty()) throw new NoPlayersRegisteredException("There are no players registered.");
		
		List<PlayerDTO> playersDto = players.stream()
					.map(s -> new PlayerDTO(s))
					.sorted(Comparator.comparingLong(PlayerDTO::getId))
					.collect(Collectors.toList());
		
		playersDto.forEach(c -> c.setAverageWinRate(getPlayerGames(players, c.getId())));
		
		return playersDto;
	}
	
	@Override
	public PlayerDTO addPlayer(Player player) throws SequenceException, DuplicateNameException {
		playerRepository.findByName(player.getName())
			.orElseThrow(() -> new DuplicateNameException("A Player with this name already exists."));
		
		Player _player = new Player(player.getName());
		_player.setId(sequenceDao.getNextSequenceId(PLAYER_SEQ_KEY));
		_player.setCreatedAt(new Date());
		
		return new PlayerDTO(playerRepository.save(_player));
	}
	
	@Override
	public PlayerDTO updatePlayer(long id, Player player) throws NotFoundException, DuplicateNameException {
		playerRepository.findByName(player.getName())
			.orElseThrow(() -> new DuplicateNameException("A Player with this name already exists."));
		
		Player _player = playerRepository.findById(id)
	        .orElseThrow(() -> new NotFoundException("Not found Player with id = " + id));
		
		_player.setName(player.getName());
		
		return new PlayerDTO(playerRepository.save(_player));
	}
	
	public double getTotalAverageWinRate()  throws NoPlayersRegisteredException {
		List<PlayerDTO> players = getAllPlayers();
		
		double totalWinRate = players.stream()
			.mapToDouble(x -> x.getAverageWinRate())
			.sum();
		
		return totalWinRate/players.size();
	}

	public PlayerDTO getWinnerPlayer() throws NoPlayersRegisteredException {
		List<PlayerDTO> players = getAllPlayers();
		
		PlayerDTO loserPlayer = players.stream()
			.max(Comparator.comparing(PlayerDTO::getAverageWinRate))
			.orElseThrow(() -> new NoPlayersRegisteredException("There are no players registered."));
		
		return loserPlayer;
	}
	
	public PlayerDTO getLoserPlayer() throws NoPlayersRegisteredException {
		List<PlayerDTO> players = getAllPlayers();
		
		PlayerDTO loserPlayer = players.stream()
			.min(Comparator.comparing(PlayerDTO::getAverageWinRate))
			.orElseThrow(() -> new NoPlayersRegisteredException("There are no players registered."));
		
		return loserPlayer;
	}
	
	private List<GameDTO> getPlayerGames(List<Player> players, long id) {
		Player player = players.stream().filter(c -> c.getId() == id)
			.findAny()
			.orElse(null);
		
		return player.getGames().stream().map(c -> new GameDTO(c)).collect(Collectors.toList());
	}
}
