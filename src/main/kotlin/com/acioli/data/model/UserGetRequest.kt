package com.acioli.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserGetRequest(
    val name: String?,
    val password: String?
)
