package com.maum.schema_patterns

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ContextConfiguration

@DataMongoTest
@ContextConfiguration(classes = [MongoTestServerConfiguration::class])
annotation class DataMongoTestDecorator
