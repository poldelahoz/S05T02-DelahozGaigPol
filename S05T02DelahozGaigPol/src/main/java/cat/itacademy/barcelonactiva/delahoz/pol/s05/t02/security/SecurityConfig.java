package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        	.authorizeHttpRequests().requestMatchers("/v3/api-docs/**", "/auth/login", "/swagger-ui/**").permitAll()
        	.anyRequest().authenticated();
	    
	    return http.build();
    }
	
}
