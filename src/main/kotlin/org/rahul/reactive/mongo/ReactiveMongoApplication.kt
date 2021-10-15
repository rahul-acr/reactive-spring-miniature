package org.rahul.reactive.mongo

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.rahul.reactive.mongo.document.Player
import org.rahul.reactive.mongo.repository.ReactivePlayerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.toFlux
import java.io.BufferedReader
import java.io.InputStreamReader

@SpringBootApplication
class ReactiveMongoApplication

fun main(args: Array<String>) {
    runApplication<ReactiveMongoApplication>(*args)
}


@Configuration
@EnableReactiveMongoRepositories
class MongoConfig : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName(): String {
        return "test_db"
    }

    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create()
    }

    override fun getMappingBasePackages(): MutableCollection<String> {
        return mutableListOf("org.rahul.reactive.mongo.document")
    }
}

@Component
class Driver(val repository: ReactivePlayerRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        repository.count().subscribe { playerCount ->
            if (playerCount == 0L) {
                val res = ClassPathResource("players.txt")
                val reader = BufferedReader(InputStreamReader(res.inputStream))
                val playersFlux = reader.lines().map { line ->
                    val split = line.split(" ")
                    Player(null, split[0], split[1])
                }.toFlux()
                repository.saveAll(playersFlux)
            }
        }
    }
}