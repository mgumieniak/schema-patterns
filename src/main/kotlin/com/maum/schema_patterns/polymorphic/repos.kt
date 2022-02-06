package com.maum.schema_patterns.polymorphic

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CommentRepository : ReactiveMongoRepository<Comment, String> {
    fun findByAuthor(author : String) : Flux<Comment>
}