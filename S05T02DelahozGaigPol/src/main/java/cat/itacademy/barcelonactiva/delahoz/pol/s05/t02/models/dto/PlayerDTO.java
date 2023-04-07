package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO.GameResult;
import lombok.Data;

@Data
public class PlayerDTO {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	private long id;
	
	private String name;

	private double averageWinRate = 0;

	private Date createdAt;
    
    public PlayerDTO(Player player) {
    	this.id = player.getId();
    	this.name = player.getName();
    	this.createdAt = player.getCreatedAt();
    }
    
    public void setAverageWinRate(List<GameDTO> games) {
    	int wins = games.stream().filter(c -> c.getResult() == GameResult.Win).collect(Collectors.toList()).size();
    	int total = games.size();
    	if (total > 0) {
    		double winRate = (double) wins/total * 100;
    		this.averageWinRate = winRate;
    	}else {
    		this.averageWinRate = 0;
    	}
    }
}
