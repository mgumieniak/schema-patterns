package com.maum.schema_patterns

import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import de.bwaldvogel.mongo.MongoServer
import de.bwaldvogel.mongo.ServerVersion
import de.bwaldvogel.mongo.backend.memory.MemoryBackend
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory

@TestConfiguration
class MongoTestServerConfiguration {

    @Bean
    fun reactiveMongoTemplate(mongoDbFactory: SimpleReactiveMongoDatabaseFactory) : ReactiveMongoTemplate =
        ReactiveMongoTemplate(mongoDbFactory)

    @Bean
    fun reactiveMongoDbFactory(mongoServer: MongoServer): SimpleReactiveMongoDatabaseFactory {
        val serverAddress = mongoServer.localAddress
        val connectionString =
            ConnectionString("mongodb://" + serverAddress.hostName + ":" + serverAddress.port + "/test")
        return SimpleReactiveMongoDatabaseFactory(connectionString)
    }

    @Bean(destroyMethod = "close")
    fun mongoClient(mongoServer: MongoServer): MongoClient =
        MongoClients.create("mongodb://" + mongoServer.localAddress.hostName + ":" + mongoServer.localAddress.port)

    @Bean(destroyMethod = "shutdown")
    fun mongoServer(): MongoServer  = MongoServer(MemoryBackend().version(ServerVersion.MONGO_3_6)).apply { bind() }

}