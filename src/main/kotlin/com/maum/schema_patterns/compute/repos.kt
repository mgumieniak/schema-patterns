package com.maum.schema_patterns.compute

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import java.time.MonthDay

interface AirConditionRepo: ReactiveMongoRepository<AirCondition, String>{
    fun findByMeasurementsDay(measurementsDay: MonthDay) : Mono<AirCondition>
}