package com.acioli.data.repository

import com.acioli.data.model.User

interface UserDatasource {

    suspend fun insertUser(user: User): Boolean
    suspend fun getUserByName(name: String): User?

    suspend fun deleteUserByName(name: String): Boolean

}
