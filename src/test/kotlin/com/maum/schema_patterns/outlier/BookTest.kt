package com.maum.schema_patterns.outlier

import com.maum.schema_patterns.DataMongoTestDecorator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@DataMongoTestDecorator
@EnableReactiveMongoRepositories(value = ["com.maum.schema_patterns.outlier"])
internal class BookTest {

    @Autowired
    lateinit var bookRepo: BookRepo

    @Autowired
    lateinit var overflowedBookCustomersRepo: OverflowedBookCustomersRepo

    @Test
    internal fun should() {
        bookRepo.save(Book("LOTR", "Tolkien", mutableListOf("maciej", "jan"))).block()
        val book = bookRepo.findByTitle("LOTR").block()
    }
}