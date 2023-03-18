package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Game;
import jakarta.transaction.Transactional;

public interface GameRepository extends JpaRepository<Game, Integer>{
	
	List<Game> findByPlayerId(Integer playerId);
	
	@Transactional
	void deleteByPlayerId(Integer playerId);
	
}
