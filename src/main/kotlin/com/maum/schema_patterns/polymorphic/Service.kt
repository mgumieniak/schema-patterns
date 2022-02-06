package com.maum.schema_patterns.polymorphic

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import java.math.BigDecimal

@Service
class Service(
    val commentRepository: CommentRepository
) {
    fun save(): Mono<Tuple2<YouTubeComment, GoogleComment>> {
        return Mono.zip(
            commentRepository.save(createYoutubeComment("Maciej")),
            commentRepository.save(createGoogleComment("Maciej"))
        )
    }

    private fun createGoogleComment(author: String) =  GoogleComment(author, "It's awesome app", BigDecimal(5))


    private fun createYoutubeComment(author: String) = YouTubeComment(author, "Awesome music", 150, 2)

}