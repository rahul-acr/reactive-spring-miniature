package org.rahul.reactive.mongo.api

import org.rahul.reactive.mongo.document.Player
import org.rahul.reactive.mongo.repository.ReactivePlayerRepository
import org.reactivestreams.Publisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/player")
class PlayerController(val repository: ReactivePlayerRepository) {

    @GetMapping
    fun fetchPlayers(): Publisher<Player> {
        return repository.findAll()
    }

}
