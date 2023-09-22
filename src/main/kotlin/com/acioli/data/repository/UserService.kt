package com.acioli.data.repository

import com.acioli.data.model.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserService (db: CoroutineDatabase): UserDatasource {

    private val users = db.getCollection<User>("Users")
    override suspend fun insertUser(user: User): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }

    override suspend fun getUserByName(name: String): User? {
        return users.findOne(User::name eq name)
    }
}