package com.maum.schema_patterns.attribute

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

class Specification(var k: String, var v: String, var u: String = "")

@Document(collection = "product")
@CompoundIndex(def = "{'specs.k': 1, 'specs.v': 1, 'specs.u': 1}", name = "spec")
class Product(
    @Indexed(unique = true) var name: String,
    var manufacturer: String,
    var price: BigDecimal,
    var specs: MutableList<Specification> = Collections.emptyList()
) {
    @Id
    var id: String? = null
}