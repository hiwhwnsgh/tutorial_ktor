package org.test.tutorial_client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createHttpClient() = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true // 응답 JSON에 정의되지 않은 필드 무시
            encodeDefaults = true // 기본값을 포함해서 직렬화
        })
    }
    install(Logging){
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
        filter { request ->
            request.url.host.contains("ktor.io")
        }
        sanitizeHeader { header -> header == HttpHeaders.Authorization }
    }
    engine {
        maxConnectionsCount = 1000
        endpoint {
            maxConnectionsPerRoute = 100
            pipelineMaxSize = 20
            keepAliveTime = 5000
            connectTimeout = 5000
            connectAttempts = 5
        }
    }
}
