package org.rahul.reactive.miniature

import org.rahul.reactive.miniature.document.Player
import org.rahul.reactive.miniature.repository.ReactivePlayerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.stereotype.Component
import org.springframework.web.reactive.config.EnableWebFlux
import reactor.kotlin.core.publisher.toFlux
import java.io.BufferedReader
import java.io.InputStreamReader

@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
class ReactiveMongoApplication

fun main(args: Array<String>) {
    runApplication<ReactiveMongoApplication>(*args)
}


@Component
class StaticDataLoader(val repository: ReactivePlayerRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        repository.count().subscribe { playerCount ->
            if (playerCount == 0L) {
                val res = ClassPathResource("players.txt")
                val reader = BufferedReader(InputStreamReader(res.inputStream))
                val playersFlux = reader.lines().map { line ->
                    val split = line.split(" ")
                    Player(null, split[0], split[1])
                }.toFlux()
                repository.saveAll(playersFlux).subscribe()
            }
        }
    }
}