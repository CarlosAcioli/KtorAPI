package com.acioli.routes

import com.acioli.data.model.UserGetRequest
import com.acioli.data.model.User
import com.acioli.data.model.UserDeleteRequest
import com.acioli.data.model.UserPostRequest
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
            val request = call.receive<UserPostRequest>()

            val user = User(
                id = ObjectId().toString(),
                name = request.name,
                password = request.password
            )

            try {
                userServiceDatabase.insertUser(user)
                call.respondText(text = "User posted with sucess", status =  HttpStatusCode.Created)

            } catch (error: Exception) {
                call.respondText (text = "An error occurred", status = HttpStatusCode.BadRequest )
            }
        }

        get("/get-user/{user}") {

            val getUserName = call.parameters["user"].toString()

            try {

                val userProvided = userServiceDatabase.getUserByName(getUserName)

                userProvided?.let { user ->

                    call.respond(message = user, status = HttpStatusCode.OK, )

                }?: call.respondText(text = "No user with name", status = HttpStatusCode.NotFound)

            } catch (e: Exception){
                call.respondText(text = "An error occurred", status = HttpStatusCode.BadRequest)
            }

        }

        delete("/user/delete") {

            val receiveUserName = call.receive<UserDeleteRequest>().name

            try {

                val isUserFounded = userServiceDatabase.getUserByName(receiveUserName)
                val deleteUser = userServiceDatabase.deleteUserByName(receiveUserName)

                if (isUserFounded?.name == receiveUserName) {

                    call.respondText(text = "User $receiveUserName deleted successfully")


                } else call.respondText(text = "no user founded")

            } catch (e: Exception) {
                call.respondText(text = "An error occurred", status = HttpStatusCode.BadRequest)
            }

        }

    }

}