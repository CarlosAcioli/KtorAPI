package com.acioli.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDeleteRequest(
    val name: String
)
