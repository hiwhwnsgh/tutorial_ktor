package org.test.tutorial_client

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform