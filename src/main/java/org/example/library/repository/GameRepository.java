package org.example.library.repository;

import org.example.library.entity.Game;
import org.example.library.enumes.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
    Optional<Game> findByGameType(GameType gameType);
}
