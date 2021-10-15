package org.rahul.reactive.mongo.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Player(
        @Id var id: String?,
        var firstName: String,
        var lastName: String
)