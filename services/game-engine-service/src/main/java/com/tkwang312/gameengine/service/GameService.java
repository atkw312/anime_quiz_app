package com.tkwang312.gameengine.service;

import com.tkwang312.gameengine.model.GameState;
import com.tkwang312.gameengine.model.GameStatus;
import com.tkwang312.gameengine.repository.GameRedisRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    private final GameRedisRepository repository;

    public GameService(GameRedisRepository repository){
        this.repository = repository;
    }

    public String startGame(List<String> playerIds){
        String gameId = UUID.randomUUID().toString();
        GameState gameState = GameState.builder()
                .gameId(gameId)
                .status(GameStatus.IN_PROGRESS)
                .playerIds(playerIds)
                .startedAt(Instant.now())
                .build();
        repository.save(gameState);

        return gameId;
    }


}
