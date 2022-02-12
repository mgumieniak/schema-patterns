package com.maum.schema_patterns.outlier

import com.maum.schema_patterns.IntegrationRepoTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationRepoTest
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