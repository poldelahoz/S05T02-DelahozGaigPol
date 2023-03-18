package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "game")
@EntityListeners(AuditingEntityListener.class)
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="dice1")
	private final short dice1;

	@Column(name="dice2")
	private final short dice2;

	@Column(name="createdAt")
	@CreatedDate
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="player_id", nullable=false)
    private Player player;
	
	public Game() {
		this.dice1 = (short) ThreadLocalRandom.current().nextInt(1, 7);
		this.dice2 = (short) ThreadLocalRandom.current().nextInt(1, 7);
	}
	
}
