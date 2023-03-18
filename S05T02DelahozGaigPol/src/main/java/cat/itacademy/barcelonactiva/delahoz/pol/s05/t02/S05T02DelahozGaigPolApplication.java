package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class S05T02DelahozGaigPolApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T02DelahozGaigPolApplication.class, args);
	}

}
