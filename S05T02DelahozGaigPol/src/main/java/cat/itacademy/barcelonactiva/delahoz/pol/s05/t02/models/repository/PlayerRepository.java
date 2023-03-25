package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Game;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
	Optional<Player> findByName(String name);
}