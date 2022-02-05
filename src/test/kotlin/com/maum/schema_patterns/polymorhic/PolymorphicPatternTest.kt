package com.maum.schema_patterns.polymorhic

import com.maum.schema_patterns.WithRepoTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

private const val AUTHOR = "Maciej"

@WithRepoTest
class PolymorphicPatternTest {

    @Autowired
    lateinit var googleCommentRepo: GoogleCommentRepository

    @Autowired
    lateinit var youTobeCommentRepo: YouTobeCommentRepository

    @Test
    internal fun shouldSaveInTheSameCollection() {
        val googleComment = createGoogleComment()
        val youtubeComment = createYoutubeComment()
        googleCommentRepo.save(googleComment).block()
        youTobeCommentRepo.save(youtubeComment).block()

        val savedGoogleComment = googleCommentRepo.findByComment_Author(AUTHOR).block()
        val savedYoutubeComment = youTobeCommentRepo.findByComment_Author(AUTHOR).block()

        checkNotNull(savedGoogleComment)
        checkNotNull(savedYoutubeComment)
        assertThat(savedYoutubeComment.comment).isEqualTo(youtubeComment.comment)
//        assertThat(savedYoutubeComment.nbLikes).isEqualTo(youtubeComment.nbLikes)
    }

    private fun createGoogleComment(author : String = AUTHOR) : GoogleComment{
        val comment = Comment(author, "It's awesome app")
        return GoogleComment(comment, BigDecimal(5))
    }

    private fun createYoutubeComment(author : String = AUTHOR) : YouTubeComment{
        val comment = Comment(author, "Awesome music")
        return YouTubeComment(comment, 150, 2)
    }

}