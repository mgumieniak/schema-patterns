package com.maum.schema_patterns.attribute

import com.maum.schema_patterns.DataMongoTestDecorator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import java.math.BigDecimal

@DataMongoTestDecorator
@EnableReactiveMongoRepositories(value = ["com.maum.schema_patterns.attribute"])
class AttributePatternTest {

    @Autowired
    lateinit var productRepo: ProductRepo

    @Test
    internal fun shouldFindByCustomData() {
        val product =
            Product(
                "Computer msi", "MSI", BigDecimal.valueOf(2500), mutableListOf(
                    Specification("color", "black"),
                    Specification("cpu", "v16", "virtual"),
                    Specification("cpu", "8", "physical"),
                )
            )
        productRepo.save(product).block()

        val products = productRepo.findProductBySpecKey("cpu")
            .collectList().block()

        assert(product.name == "Computer msi")
        assert(product.specs.size == 3)
    }
}