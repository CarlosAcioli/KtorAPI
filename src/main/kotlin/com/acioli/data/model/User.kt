package com.acioli.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class User(
    @BsonId
    val id: ObjectId,
    val name: String,
    val password: String
)
