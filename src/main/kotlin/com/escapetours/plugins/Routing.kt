package com.escapetours.plugins

import com.escapetours.routes.authRouting
import com.escapetours.routes.toursRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }

        // Tours
        toursRouting()

        // Auth
        authRouting()
    }
}
