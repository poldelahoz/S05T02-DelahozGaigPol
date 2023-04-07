package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;

public interface PlayerRepository extends MongoRepository<Player, Long>{
	Optional<Player> findByName(String name);
}