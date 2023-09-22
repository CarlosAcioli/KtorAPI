package com.acioli.routes

import com.acioli.data.model.User
import com.acioli.data.model.UserRequest
import com.acioli.data.model.UserResponse
import com.acioli.data.repository.MongoDB
import com.acioli.data.repository.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bson.types.ObjectId
import org.litote.kmongo.eq

fun Application.userRoutes() {

val userServiceDatabase = UserService(MongoDB().database)

    routing {

        post("/user") {
            val request = call.receive<UserRequest>()

            val user = User(
                id = ObjectId(),
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

        get("/{userName?}") {

            GlobalScope.launch {

                val userName = call.parameters["userName"]?: return@launch call.respondText("No id", status = HttpStatusCode.NotFound)

                val userRequest = UserResponse (
                    name = userName
                )

                val user = userServiceDatabase.getUserByName(userRequest.toString())?: return@launch call.respondText("No user with id", status = HttpStatusCode.NotFound)

                call.respond(user)

            }



        }

        get ("/user/{userName}") {

            val userName = call.parameters["userName"]

            GlobalScope.launch {

                val user = MongoDB().database.getCollection<User>("Users").findOne( User::name eq userName)?: return@launch call.respondText("Some error happened")

                call.respondText("The path worked")
                call.respond(user)

            }



        }

    }

}