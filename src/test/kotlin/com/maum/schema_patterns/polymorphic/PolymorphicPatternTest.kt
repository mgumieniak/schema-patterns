package com.maum.schema_patterns.polymorphic

import com.maum.schema_patterns.DataMongoTestDecorator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import java.math.BigDecimal

private const val AUTHOR = "Maciej"

@DataMongoTestDecorator
@EnableReactiveMongoRepositories(value = ["com.maum.schema_patterns.polymorphic"])
class PolymorphicPatternTest {

    @Autowired
    lateinit var commentRepo : CommentRepository


    @Test
    internal fun shouldSaveInTheSameCollection() {
        val googleComment = createGoogleComment()
        val youtubeComment = createYoutubeComment()
        commentRepo.save(googleComment).block()
        commentRepo.save(youtubeComment).block()

        val savedComments = commentRepo.findByAuthor(AUTHOR)
            .collectList().block()

        checkNotNull(savedComments)
        assertThat(savedComments.size).isEqualTo(2)
    }

    private fun createGoogleComment(author: String = AUTHOR) = GoogleComment(author, "It's awesome app", BigDecimal(5))

    private fun createYoutubeComment(author: String = AUTHOR) = YouTubeComment(author, "Awesome music", 150, 2)


}