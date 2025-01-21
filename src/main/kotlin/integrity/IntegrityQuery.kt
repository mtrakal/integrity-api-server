package cz.mtrakal.integrity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntegrityQuery(
    @SerialName("token")
    val token: String,
)