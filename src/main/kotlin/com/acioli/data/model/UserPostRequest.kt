package com.acioli.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserPostRequest(
    val name: String,
    val password: String
)
