package com.maum.schema_patterns.attribute

import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductRepo : ReactiveMongoRepository<Product, String>{
    fun findProductByName(name : String) : Mono<Product>

    @Query("{ 'specs.k' :  ?0 }")
    fun findProductBySpecKey(key : String) : Flux<Product>
}