package com.maum.schema_patterns.bucket

import com.maum.schema_patterns.IntegrationRepoTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant
import java.util.*

@IntegrationRepoTest
internal class SensorTest {

    @Autowired
    lateinit var sensorRepo: SensorRepo

    @Test
    internal fun shouldGetMeasurementBetweenGivenTime() {
        val sensorId = "1"
        val now = Instant.now()
        val measurements1 = LinkedList(
            listOf(
                Measurement(now.plusSeconds(30), 40),
                Measurement(now.plusSeconds(60), 38),
                Measurement(now.plusSeconds(90), 42),
                Measurement(now.plusSeconds(120), 41)
            )
        )
        val measurements2 = LinkedList(
            listOf(
                Measurement(now.plusSeconds(150), 40),
                Measurement(now.plusSeconds(180), 38),
                Measurement(now.plusSeconds(210), 42),
                Measurement(now.plusSeconds(240), 41)
            )
        )

        sensorRepo.saveAll(
            listOf(
                Sensor(sensorId, now.plusSeconds(150), now.plusSeconds(240), measurements2, 4, 121),
                Sensor(sensorId, now.plusSeconds(30), now.plusSeconds(120), measurements1, 4, 121)
            )
        ).blockLast()

        val sensorData = sensorRepo.findLastBySensorId("1", now.plusSeconds(100)).collectList().block()

        checkNotNull(sensorData)
        assert(sensorData.size == 2)
    }
}