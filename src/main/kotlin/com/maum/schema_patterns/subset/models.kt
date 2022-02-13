package com.maum.schema_patterns.subset

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "review")
class Review(var text: String) {
    @Id
    var id: String? = null

    @Indexed
    var productId: String? = null
}

const val LATEST_REVIEWS_SIZE = 1

@Document(collection = "prod")
class Product(
    @Indexed(unique = true) var name: String,
    var description: String
) {
    @Id
    var id: String? = null

    var latestReviews: LinkedList<Review> = LinkedList()

    fun addReview(review: Review): ProductWithOverflowedReview {
        review.productId = id
        latestReviews.add(review)

        return if (latestReviews.size > LATEST_REVIEWS_SIZE) {
            val polledReview = latestReviews.poll()
            ProductWithOverflowedReview(this, polledReview)
        } else {
            ProductWithOverflowedReview(this, null)
        }
    }
}

data class ProductWithOverflowedReview(val product: Product, val overflowedReview: Review?)