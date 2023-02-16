package com.suzintech.gameawards.controller;

import com.suzintech.gameawards.domain.Game;
import com.suzintech.gameawards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("games/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("games")
    public ResponseEntity<Game> insert(@RequestBody Game game) {
        service.insert(game);

        return ResponseEntity.ok(game);
    }

    @PutMapping("games/{id}")
    public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
        service.update(id, game);

        return ResponseEntity.ok(game);
    }

    @DeleteMapping("games/{id}")
    public ResponseEntity<Game> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
