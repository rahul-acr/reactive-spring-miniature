package org.rahul.reactive.miniature.api

import org.rahul.reactive.miniature.document.Player
import org.rahul.reactive.miniature.repository.ReactivePlayerRepository
import org.reactivestreams.Publisher
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/player")
class PlayerController(val repository: ReactivePlayerRepository) {

    @GetMapping
    fun fetchPlayers(): Publisher<Player> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun fetchPlayer(@PathVariable id: String): Publisher<Player> {
        return repository.findById(id)
    }

    @PostMapping
    fun addPlayer(@RequestBody player: Player): Publisher<Player> {
        return repository.save(player)
    }

}
