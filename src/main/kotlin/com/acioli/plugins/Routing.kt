package com.acioli.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/testing"){
            call.respond("Testing hello world")
        }
    }
}
