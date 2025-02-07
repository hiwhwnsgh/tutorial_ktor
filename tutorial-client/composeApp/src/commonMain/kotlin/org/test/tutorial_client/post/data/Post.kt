package org.test.tutorial_client.post.data

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val userId : Long,
    val id : Long,
    val title : String,
    val body : String
)
