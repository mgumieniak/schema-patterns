package com.maum.schema_patterns.bucket

import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant

interface SensorRepo : ReactiveMongoRepository<Sensor, String> {

    @Query(value = "{ 'sensorId' : ?0, 'endDate' : { \$gte :  ?1 } }", sort = "{ startDate : 1 }")
    fun findLastBySensorId(sensorId: String, startMeasureDate: Instant): Flux<Sensor>

    fun findBySensorId(sensorId: String): Flux<Sensor>
}
