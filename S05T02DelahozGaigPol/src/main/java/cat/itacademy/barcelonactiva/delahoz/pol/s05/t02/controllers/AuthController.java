package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Auth", description = "The Auth API. Contains the login operation to get the JWT token for bearer authentication.")
public class AuthController {

	@Autowired
	JwtService jwtService;
	
	@Operation(summary = "Login to API")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "text/plain",
		    		schema = @Schema(implementation = String.class))}),
		  @ApiResponse(responseCode = "403", description = "Forbidden", 
		    content = @Content),
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@GetMapping("/auth/login")
	public ResponseEntity<String> login(@RequestParam(name="username") String username) {
		try {
			String jwt = jwtService.getJwt(username);
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
