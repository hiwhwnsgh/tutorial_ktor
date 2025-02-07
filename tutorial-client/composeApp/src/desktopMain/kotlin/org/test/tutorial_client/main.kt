package org.test.tutorial_client

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "tutorial-client",
    ) {
        App()
    }
}