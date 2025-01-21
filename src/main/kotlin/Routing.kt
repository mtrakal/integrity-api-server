package cz.mtrakal

import cz.mtrakal.integrity.DecodeTokenUseCase
import cz.mtrakal.integrity.integrityRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    decodeTokenUseCase: DecodeTokenUseCase
) {
    routing {
        get("/") {
            call.respondText("It works!")
        }

        integrityRouting(decodeTokenUseCase)
    }
}

