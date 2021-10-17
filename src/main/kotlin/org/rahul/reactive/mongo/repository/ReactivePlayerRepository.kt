package org.rahul.reactive.mongo.repository

import org.rahul.reactive.mongo.document.Player
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReactivePlayerRepository : ReactiveCrudRepository<Player, String>