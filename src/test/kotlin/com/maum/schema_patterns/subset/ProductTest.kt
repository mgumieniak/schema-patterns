package com.maum.schema_patterns.subset

import com.maum.schema_patterns.DataMongoTestDecorator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import reactor.test.StepVerifier

@Import(value = [ProductRepoDecorator::class])
@DataMongoTestDecorator
@EnableReactiveMongoRepositories(value = ["com.maum.schema_patterns.subset"])
class ProductTest {

    @Autowired
    lateinit var reviewRepo: ReviewRepo

    @Autowired
    lateinit var productRepoDecorator: ProductRepoDecorator

    @Test
    internal fun shouldThrowExcWhenTryToAddReviewsForNotExistedProduct() {
        val product = productRepoDecorator.addReviewToProduct("not existed", Review("It's great"))

        StepVerifier.create(product)
            .expectError(IllegalArgumentException::class.java)
            .verify()
    }

    @Test
    internal fun should() {
        val productName = "TV"
        val firstRevTxt = "Great!!"
        val secRexTxt = "Awesome!!"
        val productToSave = Product(productName, "Tv lg")
        productRepoDecorator.save(productToSave).block()

        productRepoDecorator.addReviewToProduct(productName, Review(firstRevTxt)).block()
        productRepoDecorator.addReviewToProduct(productName, Review(secRexTxt)).block()

        val product = productRepoDecorator.findByName(productName).block()
        checkNotNull(product)
        assert(product.latestReviews[0].text == secRexTxt)
        checkNotNull(product.latestReviews[0].productId)

        val reviews = reviewRepo.findByProductId(product.id!!).collectList().block()
        checkNotNull(reviews)
        assert(reviews.size == 1)
        assert(reviews[0].text == firstRevTxt)
    }
}