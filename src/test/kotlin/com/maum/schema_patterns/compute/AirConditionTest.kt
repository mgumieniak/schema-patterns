package com.maum.schema_patterns.compute

import com.maum.schema_patterns.DataMongoTestDecorator
import org.junit.jupiter.api.Test
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@DataMongoTestDecorator
@EnableReactiveMongoRepositories(value = ["com.maum.schema_patterns.compute"])
internal class AirConditionTest {

    @Test
    internal fun shouldAddMeasureAndCalculateAvg() {
    }
}