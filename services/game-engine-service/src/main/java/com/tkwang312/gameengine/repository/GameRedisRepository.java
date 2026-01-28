package com.tkwang312.gameengine.repository;

import com.tkwang312.gameengine.model.GameState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public class GameRedisRepository {

    private static final String KEY_PREFIX = "game: ";
    private final RedisTemplate<String, GameState> redisTemplate;

    public GameRedisRepository(@Qualifier("gameRedisTemplate") RedisTemplate<String, GameState> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(GameState gameState) {
        redisTemplate.opsForValue().set(
                KEY_PREFIX + gameState.getGameId(),
                gameState,
                Duration.ofHours(1)
        );
    }

    public Optional<GameState> findById(String gameId) {
        return Optional.ofNullable(
                redisTemplate.opsForValue().get(key(gameId))
        );
    }

    public void delete(String gameId) {
        redisTemplate.delete(key(gameId));
    }

    private String key(String gameId) {
        return KEY_PREFIX + gameId;
    }

}
