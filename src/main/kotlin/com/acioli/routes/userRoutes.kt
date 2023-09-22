package com.acioli.routes

import com.acioli.data.model.User
import com.acioli.data.model.UserGetRequest
import com.acioli.data.model.UserRequest
import com.acioli.data.repository.MongoDB
import com.acioli.data.repository.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Application.userRoutes() {

val userServiceDatabase = UserService(MongoDB().database)

    routing {

        post("/user") {
            val request = call.receive<UserRequest>()

            val user = User(
                id = ObjectId().toString(),
                name = request.name,
                password = request.password
            )

            try {
                userServiceDatabase.insertUser(user)
                call.respondText("User posted with sucess", status =  HttpStatusCode.Created)

            } catch (error: Exception) {
                call.respondText ( "An error occurred", status = HttpStatusCode.BadRequest )
            }
        }

        get("/get-user") {

            val userName = call.receive<UserGetRequest>().name

            val user = userServiceDatabase.getUserByName(userName)

            user?.let {

                call.respond(
                    HttpStatusCode.OK,
                    it
                )

            }?: call.respond( HttpStatusCode.BadRequest, "No user with name" )


        }

    }

}