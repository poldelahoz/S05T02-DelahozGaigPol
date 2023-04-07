package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
	
	@Id
	private long id;

	private short dice1;

	private short dice2;

	private Date createdAt;
	
	public Game() {
		this.dice1 = (short) ThreadLocalRandom.current().nextInt(1, 7);
		this.dice2 = (short) ThreadLocalRandom.current().nextInt(1, 7);
	}
	
}
