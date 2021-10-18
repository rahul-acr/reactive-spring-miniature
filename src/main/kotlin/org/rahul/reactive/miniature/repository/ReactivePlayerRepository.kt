package org.rahul.reactive.miniature.repository

import org.rahul.reactive.miniature.document.Player
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReactivePlayerRepository : ReactiveCrudRepository<Player, String>