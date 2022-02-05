package com.maum.schema_patterns.polymorhic

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface YouTobeCommentRepository : ReactiveMongoRepository<YouTubeComment, String> {
    fun findByComment_Author(author : String) : Mono<YouTubeComment>
}


@Repository
interface GoogleCommentRepository : ReactiveMongoRepository<GoogleComment, String> {
    fun findByComment_Author(author : String) : Mono<GoogleComment>
}