package com.maum.schema_patterns.subset

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

interface ProductRepo : ReactiveMongoRepository<Product, String> {
    fun findByName(name: String): Mono<Product>
}

interface ReviewRepo : ReactiveMongoRepository<Review, String> {
    fun findByProductId(productId: String): Flux<Review>
}

@Repository
class ProductRepoDecorator(val productRepo: ProductRepo, val reviewRepo: ReviewRepo) : ProductRepo by productRepo {

    fun addReviewToProduct(productName: String, review: Review): Mono<Product> {
        return findByName(productName)
            .switchIfEmpty { throw IllegalArgumentException("Product with name: $productName not found") }
            .map { product -> product.addReview(review) }
            .flatMap { productWithOverflowedReview ->
                val overflowedReview = productWithOverflowedReview.overflowedReview
                val product = productWithOverflowedReview.product

                if (overflowedReview == null) {
                    productRepo.save(product)
                } else {
                    reviewRepo.save(overflowedReview)
                        .zipWith(productRepo.save(product))
                        .then(Mono.fromCallable { product })
                }
            }
    }


}