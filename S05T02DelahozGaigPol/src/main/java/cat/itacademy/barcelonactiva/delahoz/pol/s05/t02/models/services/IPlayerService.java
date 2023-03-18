package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services;

import java.util.List;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.PlayerDTO;

public interface IPlayerService {

	List<PlayerDTO> getAllPlayers();
	
	double getTotalAverageWinRate();
	
	PlayerDTO getLoserPlayer() throws NotFoundException;	
	
	PlayerDTO getWinnerPlayer() throws NotFoundException;	
	
	PlayerDTO addPlayer(Player player);

	PlayerDTO updatePlayer(Integer id, Player player) throws NotFoundException;
}
