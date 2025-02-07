package org.test.tutorial_client.post.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostApi(private val client: HttpClient) {
    suspend fun getAllPosts(): List<Post> {
        return client.get("https://jsonplaceholder.typicode.com/posts").body()
    }
}