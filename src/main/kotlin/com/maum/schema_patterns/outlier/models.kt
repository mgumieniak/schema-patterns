package com.maum.schema_patterns.outlier

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "book")
class Book(@Indexed var title: String, @Indexed var author: String, var customers: MutableList<String>){
    @Id var id: String? = null
    var isOverflowed: Boolean = false
}

@Document(collection = "overflowed_book_customer")
class OverflowedBookCustomers(@Indexed var bookId: String, var customers: MutableList<String>){
    @Id var id: String? = null
}