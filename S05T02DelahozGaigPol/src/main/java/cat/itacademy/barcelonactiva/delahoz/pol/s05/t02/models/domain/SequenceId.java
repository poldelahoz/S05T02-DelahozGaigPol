package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sequence")
public class SequenceId {
	@Id
	private String id;

	private long seq;
}
