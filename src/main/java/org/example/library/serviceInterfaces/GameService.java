package org.example.library.serviceInterfaces;

import org.example.library.entity.Game;
import org.example.library.enumes.GameType;

import java.util.List;

public interface GameService {
    Game createGame(Game game);

    Game getGameById(Long id);

    Game getGameByGameType(GameType gameType);

    List<Game> getAllGame();

    void deleteById(Long id);

    Game updateGame(Long id, Game game);

    Game updatePartialGame(Long id, Game game);

}
