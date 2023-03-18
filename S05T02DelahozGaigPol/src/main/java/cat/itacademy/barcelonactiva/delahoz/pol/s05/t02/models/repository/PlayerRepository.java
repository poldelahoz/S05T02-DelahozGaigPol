package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
}