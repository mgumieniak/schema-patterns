package com.maum.schema_patterns.polymorhic

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant
import java.util.*

class Comment(var author: String, var message: String){
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

@Document(collation = "Comment")
class YouTubeComment(var comment: Comment, var nbLikes: Long, var ngDisLikes: Long){
    @Id var id : String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YouTubeComment

        if (comment != other.comment) return false
        if (nbLikes != other.nbLikes) return false
        if (ngDisLikes != other.ngDisLikes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = comment.hashCode()
        result = 31 * result + nbLikes.hashCode()
        result = 31 * result + ngDisLikes.hashCode()
        return result
    }

    override fun toString(): String {
        return "YouTubeComment(comment=$comment, nbLikes=$nbLikes, ngDisLikes=$ngDisLikes, id=$id)"
    }
}

@Document(collation = "Comment")
class GoogleComment(var comment: Comment, var rate: BigDecimal){
    @Id var id : String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GoogleComment

        if (comment != other.comment) return false
        if (rate != other.rate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = comment.hashCode()
        result = 31 * result + rate.hashCode()
        return result
    }

    override fun toString(): String {
        return "GoogleComment(comment=$comment, rate=$rate, id=$id)"
    }
}

/**
 * 1. Use composition over inheritance
 * 2. The data stored about each comment does not need to be the same even though the documents are in the same collection.
 *
 */