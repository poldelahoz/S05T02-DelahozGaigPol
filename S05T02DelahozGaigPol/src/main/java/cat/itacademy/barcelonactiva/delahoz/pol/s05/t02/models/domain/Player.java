package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
@EntityListeners(AuditingEntityListener.class)
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;

	@Column(name="name",unique=true)
	private String name;

	@Column(name="createdAt")
	@CreatedDate
	@JsonIgnore
	private Date createdAt;
	
	@OneToMany(mappedBy="player", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
    private List<Game> games = new ArrayList<Game>();;
	
	public Player(String name) {
		
		this.name = name;
	}
}