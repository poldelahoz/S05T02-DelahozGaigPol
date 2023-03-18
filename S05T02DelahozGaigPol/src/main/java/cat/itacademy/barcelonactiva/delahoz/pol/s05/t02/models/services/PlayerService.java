package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.awt.desktop.AppForegroundEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Game;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.GameRepository;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository.PlayerRepository;

@Service
public class PlayerService implements IPlayerService{
	
	@Autowired
  	PlayerRepository playerRepository;
	
	@Autowired
  	GameRepository gameRepository;
	
	@Override
	public List<PlayerDTO> getAllPlayers() {
		List<PlayerDTO> players = playerRepository.findAll()
					.stream()
					.map(s -> new PlayerDTO(s))
					.sorted((s1, s2) -> s1.getId().compareTo(s2.getId()))
					.collect(Collectors.toList());
		players.forEach(c -> c.setAverageWinRate(getPlayerGames(c.getId())));
		return players;
	}
	
	@Override
	public PlayerDTO addPlayer(Player player) {
		Player _player = new Player(player.getName());
		PlayerDTO playerDto = new PlayerDTO(playerRepository.save(_player));
		return playerDto;
	}
	
	@Override
	public PlayerDTO updatePlayer(Integer id, Player player) throws NotFoundException {
		Player _player = playerRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Not found Player with id = " + id));
		
		_player.setName(player.getName());
		PlayerDTO playerDto = new PlayerDTO(playerRepository.save(player));
		return playerDto;
	}
	
	public double getTotalAverageWinRate() {
		List<PlayerDTO> players = getAllPlayers();
		double totalWinRate = players.stream()
						.mapToDouble(x -> x.getAverageWinRate())
						.sum();
		return totalWinRate/players.size();
	}

	public PlayerDTO getWinnerPlayer() throws NotFoundException {
		List<PlayerDTO> players = getAllPlayers();
		PlayerDTO loserPlayer = players.stream()
									.max(Comparator.comparing(PlayerDTO::getAverageWinRate))
									.orElseThrow(() -> new NotFoundException("There are no players registered."));
		return loserPlayer;
	}
	
	public PlayerDTO getLoserPlayer() throws NotFoundException {
		List<PlayerDTO> players = getAllPlayers();
		PlayerDTO loserPlayer = players.stream()
									.min(Comparator.comparing(PlayerDTO::getAverageWinRate))
									.orElseThrow(() -> new NotFoundException("There are no players registered."));
		return loserPlayer;
	}
	
	private List<GameDTO> getPlayerGames(Integer id) {
		return gameRepository.findByPlayerId(id).stream().map(c -> new GameDTO(c)).collect(Collectors.toList());
	}
}
