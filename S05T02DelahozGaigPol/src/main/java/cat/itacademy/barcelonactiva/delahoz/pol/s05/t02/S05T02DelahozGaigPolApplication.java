package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@EnableJpaAuditing
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP)
@OpenAPIDefinition(info = @Info(title = "Dice Game API", version = "1.0", description = ""))
public class S05T02DelahozGaigPolApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T02DelahozGaigPolApplication.class, args);
	}

}
