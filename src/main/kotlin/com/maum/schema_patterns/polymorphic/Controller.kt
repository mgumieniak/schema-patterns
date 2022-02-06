package com.maum.schema_patterns.polymorphic

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2

@RestController
@RequestMapping("/polymorphic")
class Controller(val service: Service) {

    @GetMapping("/test")
    fun save(): Mono<Tuple2<YouTubeComment, GoogleComment>> = service.save()
}