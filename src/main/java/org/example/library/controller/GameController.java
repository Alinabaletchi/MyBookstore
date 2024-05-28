package org.example.library.controller;

import org.example.library.entity.Game;
import org.example.library.enumes.GameType;
import org.example.library.serviceInterfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin("http://localhost:4200")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/createGames")
    public Game createGame(@RequestBody Game game){

        return gameService.createGame(game);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(Long id) {
        Game game = gameService.getGameById(id);
        if (null == game) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("games/type/{type}")
    public ResponseEntity<Game> getGameByGameType(GameType gameType){
        Game game = gameService.getGameByGameType(gameType);
        if (null==game){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
    @GetMapping("/allGames")

    public ResponseEntity<List<Game>> getAllGames(){

        return ResponseEntity.ok(gameService.getAllGame());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGame(@PathVariable Long id){
        Game game = gameService.getGameById(id);
        if (game==null){
            return ResponseEntity.notFound().build();
        }
        gameService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        if (null == updatedGame) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGame);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Game> updatePartialGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.updatePartialGame(id, game);
        if (null == updatedGame) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGame);
    }

}
