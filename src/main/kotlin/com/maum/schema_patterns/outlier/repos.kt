package com.maum.schema_patterns.outlier

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BookRepo : ReactiveMongoRepository<Book, String> {
    fun findByAuthor(author: String): Mono<Book>

    fun findByTitle(title: String): Mono<Book>
}

interface OverflowedBookCustomersRepo : ReactiveMongoRepository<OverflowedBookCustomers, String>{
    fun findByBookId(bookId: String): Flux<OverflowedBookCustomers>
}
