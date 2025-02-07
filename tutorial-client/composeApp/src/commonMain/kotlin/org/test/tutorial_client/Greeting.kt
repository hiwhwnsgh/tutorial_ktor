package org.test.tutorial_client

import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Greeting {
    private val client = HttpClient(CIO)

    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}