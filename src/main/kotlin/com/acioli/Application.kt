package com.acioli

import com.acioli.data.model.User
import com.acioli.plugins.*
import com.acioli.routes.userRoutes
import com.mongodb.MongoException
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bson.BsonInt64
import org.bson.Document
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    userRoutes()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
