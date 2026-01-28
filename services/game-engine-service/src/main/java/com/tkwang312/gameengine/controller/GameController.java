package com.tkwang312.gameengine.controller;

import com.tkwang312.gameengine.service.GameService;
import com.tkwang312.gameengine.dto.StartGameRequest;
import com.tkwang312.gameengine.dto.StartGameResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public StartGameResponse startGame(@Valid @RequestBody StartGameRequest request){
        String gameId = gameService.startGame(request.getPlayerIds());
        return new StartGameResponse(gameId);
    }
}
