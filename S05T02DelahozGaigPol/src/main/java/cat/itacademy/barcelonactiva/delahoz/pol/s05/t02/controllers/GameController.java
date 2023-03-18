package cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.dto.GameDTO;
import cat.itacademy.barcelonactiva.delahoz.pol.s05.t02.models.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@Operation(summary = "Returns all games from a player")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "200", description = "Ok", 
		    content = { @Content(mediaType = "application/json",
		    		array = @ArraySchema(schema = @Schema(implementation = GameDTO.class)))}),
		  @ApiResponse(responseCode = "204", description = "No content", 
		    content = @Content),
		  @ApiResponse(responseCode = "404", description = "Not found", 
		    content = @Content),
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@GetMapping("/players/{id}/games")
	public ResponseEntity<List<GameDTO>> getAll(@PathVariable("id") Integer id) {
		try {
			List<GameDTO> games = gameService.getAllGames(id);
			if (games.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(games, HttpStatus.OK);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "A player plays a game")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "201", description = "Created", 
		    content = { @Content(mediaType = "application/json",
		    		schema = @Schema(implementation = GameDTO.class))}),
		  @ApiResponse(responseCode = "404", description = "Not found", 
		    content = @Content),
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@PostMapping("/players/{id}/games")
	public ResponseEntity<GameDTO> add(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(gameService.newGame(id), HttpStatus.CREATED);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Deletes all games from a player")
	@ApiResponses(value = { 
		  @ApiResponse(responseCode = "204", description = "No Content", 
		    content = { @Content(mediaType = "application/json")}),
		  @ApiResponse(responseCode = "404", description = "Not found", 
		    content = @Content),
		  @ApiResponse(responseCode = "500", description = "Internal server error", 
		    content = @Content) })
	@DeleteMapping("/players/{id}/games")
	public ResponseEntity<GameDTO> deleteAll(@PathVariable("id") Integer id) {
		try {
			gameService.deleteAllGames(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
