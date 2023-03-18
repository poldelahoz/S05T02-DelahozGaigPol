package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto;

import java.util.Date;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Game;
import lombok.Data;

@Data
public class GameDTO {
	
	private short dice1;

	private short dice2;
	
	private short totalPoints;
	
	private GameResult result;

	private Date createdAt;
	
	public GameDTO(Game game) {
		this.dice1 = game.getDice1();
		this.dice2 = game.getDice2();
		this.totalPoints = (short) Math.addExact(dice1, dice2);
		if (totalPoints == 7) {
			this.result = GameResult.Win;
		}else {
			this.result = GameResult.Lose;
		}
		this.createdAt = game.getCreatedAt();
	}
	
	public enum GameResult{
		Win,
		Lose
	}
}
