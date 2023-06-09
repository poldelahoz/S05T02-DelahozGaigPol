package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.DuplicateNameException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NoPlayersRegisteredException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.domain.Player;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services.PlayerService;

@RestController
@RequestMapping("/players")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Player", description = "The Player API. Contains all the operations that can be performed on a player.")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	@Operation(summary = "Returns all players with their average win rate")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "application/json",
		    		array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))}),
		  @ApiResponse(responseCode = "204", description = "No content", 
		    content = @Content), 
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@GetMapping
	public ResponseEntity<List<PlayerDTO>> getAll() {
		try {
			return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
		}
		catch (NoPlayersRegisteredException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Creates a player")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "201", description = "Created", 
		    content = { @Content(mediaType = "application/json",
		    		schema = @Schema(implementation = PlayerDTO.class))}),
		  @ApiResponse(responseCode = "409", description = "Player name conflict", 
		    content = @Content), 
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@PostMapping
	public ResponseEntity<PlayerDTO> add(@RequestBody Player player) {
		try {
			return new ResponseEntity<>(playerService.addPlayer(player), HttpStatus.CREATED);
		}
		catch (DuplicateNameException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Updates player's name")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "application/json",
		    		schema = @Schema(implementation = PlayerDTO.class))}),
		  @ApiResponse(responseCode = "404", description = "Not found", 
		    content = @Content), 
		  @ApiResponse(responseCode = "409", description = "Player name conflict", 
		    content = @Content), 
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@PutMapping
	public ResponseEntity<PlayerDTO> update(@RequestParam("id") long id, @RequestBody Player player) {
		try {
			return new ResponseEntity<>(playerService.updatePlayer(id, player), HttpStatus.OK);
		}
		catch (DuplicateNameException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		catch (NotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Returns average win rate of all players")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "application/json",
		    		array = @ArraySchema(schema = @Schema(implementation = Double.class)))}),
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@GetMapping("/ranking")
	public ResponseEntity<Double> getRanking() {
		try {
			return new ResponseEntity<>(playerService.getTotalAverageWinRate(), HttpStatus.OK);
		}
		catch (NoPlayersRegisteredException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Returns the player with higher average win rate")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "application/json",
		    		array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))}),
		  @ApiResponse(responseCode = "204", description = "No content", 
		    content = @Content), 
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@GetMapping("/ranking/winner")
	public ResponseEntity<PlayerDTO> getWinnerPlayer() {
		try {
			return new ResponseEntity<>(playerService.getWinnerPlayer(), HttpStatus.OK);
		}
		catch (NoPlayersRegisteredException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Returns the player with lower average win rate")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "application/json",
		    		array = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))}),
		  @ApiResponse(responseCode = "204", description = "No content", 
		    content = @Content), 
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@GetMapping("/ranking/loser")
	public ResponseEntity<PlayerDTO> getLoserPlayer() {
		try {
			return new ResponseEntity<>(playerService.getLoserPlayer(), HttpStatus.OK);
		}
		catch (NoPlayersRegisteredException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
