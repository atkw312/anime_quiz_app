package com.tkwang312.gameengine.model;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameState {

    @NotNull
    private String gameId;

    @NotNull
    private String lobbyId;

    @NotNull
    private GameStatus status;

    @NotNull
    private List<String> playerIds;

    private Map<String, Integer> guesses;

    private Instant startedAt;
    private Instant completedAt;
}
