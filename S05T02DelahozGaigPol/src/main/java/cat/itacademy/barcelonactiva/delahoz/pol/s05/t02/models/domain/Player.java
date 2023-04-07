package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Player")
public class Player {
	
	@Id
	@JsonIgnore
	private long id;

	private String name;

	@JsonIgnore
	private Date createdAt;
	
	@JsonIgnore
    private List<Game> games = new ArrayList<Game>();;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void addGame(Game game) {
		this.games.add(game);
	}
}