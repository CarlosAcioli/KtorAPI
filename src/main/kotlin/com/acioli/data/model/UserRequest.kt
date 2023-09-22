package com.acioli.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val name: String,
    val password: String
)
