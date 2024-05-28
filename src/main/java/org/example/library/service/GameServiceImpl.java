package org.example.library.service;

import org.example.library.entity.Game;
import org.example.library.enumes.GameType;
import org.example.library.repository.GameRepository;
import org.example.library.serviceInterfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return game.get();
        } else {

            return null;
        }
    }

    @Override
    public List<Game> getGamesByGameType(GameType gameType) {
        Optional<List<Game>> game = gameRepository.findByGameType(gameType);
        if (game.isPresent()) {
            return game.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);

    }

    @Override
    public Game updateGame(Long id, Game game) {
        Optional<Game> foundGame = gameRepository.findById(id);
        if (foundGame.isPresent()) {
            Game updateGame = foundGame.get();
            updateGame.setName(game.getName());
            updateGame.setDescription(game.getDescription());
            updateGame.setPrice(game.getPrice());
            updateGame.setQuantity(game.getQuantity());
            updateGame.setImage(game.getImage());
            return gameRepository.save(updateGame);
        } else {
            return null;
        }
    }

    @Override
    public Game updatePartialGame(Long id, Game game) {
        Optional<Game> foundGame = gameRepository.findById(id);
        if (foundGame.isPresent()) {
            Game updateGame = foundGame.get();
            if (null != game.getName()) {
                updateGame.setName(game.getName());
            }
            if (null != game.getDescription()) {
                updateGame.setDescription(game.getDescription());
            }
            if (0 != game.getPrice()) {
                updateGame.setPrice(game.getPrice());
            }
            if (0 != game.getQuantity()) {
                updateGame.setQuantity(game.getQuantity());
            }
            if (null != game.getImage()) {
                updateGame.setImage(game.getImage());
            }
            return gameRepository.save(updateGame);
        } else {
            return null;
        }
    }
}
