package com.maum.schema_patterns.compute

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.MonthDay
import java.time.ZoneOffset

@Document(collection = "sensor")
class AirCondition {
    @Id
    var id: String? = null

    @Indexed
    val measurementsDay: MonthDay = MonthDay.now(ZoneOffset.UTC)

    var measurements: MutableList<BigDecimal> = mutableListOf()

    var nbMeasurements: BigDecimal = BigDecimal.ZERO

    var avgMeasure: BigDecimal = BigDecimal.ZERO

    fun addMeasure(measure: BigDecimal) {
        measurements.add(measure)

        val incrementedNbMeasurements = nbMeasurements.plus(BigDecimal.ONE)
        avgMeasure = (avgMeasure.multiply(nbMeasurements).plus(measure)).divide(incrementedNbMeasurements)

        nbMeasurements = incrementedNbMeasurements

    }
}