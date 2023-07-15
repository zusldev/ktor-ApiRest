package com.escapetours.routes

import com.escapetours.data.Tours
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val BASE_URL = "http://192.168.1.7:8080/"

private val tours = listOf(
    Tours(
        1,
        "Paris Tour",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        99.99,
        "${BASE_URL}images/paris.jpg",
        4.3,
        "Paris",
    ),
    Tours(
        2,
        "Rome Tour",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        149.99,
        "${BASE_URL}images/rome.jpg",
        4.7,
        "Rome",
    ),
    Tours(
        3,
        "London Tour",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        89.99,
        "${BASE_URL}images/london.jpg",
        3.8,
        "London",
    ),
    Tours(
        4,
        "Venice Tour",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        89.99,
        "${BASE_URL}images/venice.jpg",
        4.1,
        "Venice",
    ),
    Tours(
        5,
        "New York Tour",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        189.99,
        "${BASE_URL}images/newyork.jpg",
        4.9,
        "New York",
    ),
)

fun Routing.toursRouting() {
    get("/tours") {
        call.respond(
            HttpStatusCode.OK,
            tours
        )
    }

    get("/tours/{id}") {
        val id = call.parameters["id"]
        val tour = tours.find { it.id == id?.toInt() }
        if (tour == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "Tour with id $id not found"

            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                tour
            )
        }
    }
}
