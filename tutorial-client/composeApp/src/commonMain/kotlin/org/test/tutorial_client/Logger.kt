package org.test.tutorial_client

import org.slf4j.LoggerFactory
import tutorial_client.composeapp.generated.resources.AppLogger
import tutorial_client.composeapp.generated.resources.Res

object Logger {
    private val logger = LoggerFactory.getLogger("AppLogger") // 올바른 방식

    fun info(message: String) {
        logger.info(message)
    }

    fun warn(message: String) {
        logger.warn(message)
    }

    fun debug(message: String) {
        logger.debug(message)
    }

    fun error(message: String) {
        logger.error(message)
    }
}