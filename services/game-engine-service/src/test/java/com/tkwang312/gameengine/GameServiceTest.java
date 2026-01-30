package com.tkwang312.gameengine;

import com.tkwang312.gameengine.model.GameState;
import com.tkwang312.gameengine.repository.GameRedisRepository;
import com.tkwang312.gameengine.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRedisRepository repository;

    @InjectMocks
    private GameService gameService;

    @Test
    void startGame_createsAndStoresGameState() {
        List<String> players = List.of("u1", "u2");

        String gameId = gameService.startGame(players);

        assertNotNull(gameId);
        verify(repository).save(any(GameState.class));
    }
}
