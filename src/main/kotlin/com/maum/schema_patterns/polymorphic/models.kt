package com.maum.schema_patterns.polymorphic

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant
import java.util.*

abstract class Comment(var author: String, var message: String) {

    var _class: String = ""

    var commentId: String = UUID.randomUUID().toString()

    var createDate: Instant = Instant.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (author != other.author) return false
        if (message != other.message) return false
        if (commentId != other.commentId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = author.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + commentId.hashCode()
        return result
    }

    override fun toString(): String {
        return "Comment(author='$author', message='$message', commentId='$commentId', createDate=$createDate)"
    }
}

@TypeAlias("YouTubeComment")
@Document(collection = "comment")
class YouTubeComment(author: String, message: String, var nbLikes: Long, var ngDisLikes: Long) :
    Comment(author, message) {

    @Id
    var id: String? = null

}

@TypeAlias("GoogleComment")
@Document(collection = "comment")
class GoogleComment(author: String, message: String, var rate: BigDecimal) : Comment(author, message) {
    @Id
    var id: String? = null

}