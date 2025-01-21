package cz.mtrakal.integrity

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.integrityRouting(decodeTokenUseCase: DecodeTokenUseCase) {
    post<IntegrityQuery>("/play/integrity/all") { query ->
        val response = decodeTokenUseCase(query.token)
            ?.tokenPayloadExternal
            .orEmpty()
            .toString()
        call.respondText(response)
    }
}