package com.maum.schema_patterns.bucket

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*


class Measurement(var timestamp: Instant, var data: Long)

@Document(collection = "sensor")
class Sensor(
    @Indexed var sensorId: String,
    var startDate: Instant,
    var endDate: Instant,
    var measurements: LinkedList<Measurement>,
    var nbOfMeasurements: Long,
    var sumOfMeasurements: Long,
){
    @Id
    var id: String? = null
}