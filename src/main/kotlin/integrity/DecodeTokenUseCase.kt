package cz.mtrakal.integrity

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.playintegrity.v1.PlayIntegrity
import com.google.api.services.playintegrity.v1.model.DecodeIntegrityTokenRequest
import com.google.api.services.playintegrity.v1.model.DecodeIntegrityTokenResponse


interface DecodeTokenUseCase {
    /**
     * Response should look like:
     *
     * ```json
     * {
     * "tokenPayloadExternal": {
     *     "accountDetails": {
     *         "appLicensingVerdict": "LICENSED"
     *     },
     *     "appIntegrity": {
     *         "appRecognitionVerdict": "PLAY_RECOGNIZED",
     *         "certificateSha256Digest": ["pnpa8e8eCArtvmaf49bJE1f5iG5-XLSU6w1U9ZvI96g"],
     *         "packageName": "com.test.android.integritysample",
     *         "versionCode": "4"
     *     },
     *     "deviceIntegrity": {
     *         "deviceRecognitionVerdict": ["MEETS_DEVICE_INTEGRITY"]
     *     },
     *     "requestDetails": {
     *         "nonce": "SafetyNetSample1654058651834",
     *         "requestPackageName": "com.test.android.integritysample",
     *         "timestampMillis": "1654058657132"
     *     }
     * }
     * ```
     */
    operator fun invoke(token: String): DecodeIntegrityTokenResponse?
}

class DecodeTokenUseCaseImpl(
    private val httpTransport: HttpTransport,
    private val jsonFactory: JsonFactory,
    private val googleClientRequestInitializer: GoogleClientRequestInitializer,
    private val applicationName: ApplicationName,
    private val packageName: PackageName,
    private val integrityTokenRequest: DecodeIntegrityTokenRequest,
    private val requestInitializer: HttpRequestInitializer,
) : DecodeTokenUseCase {
    override fun invoke(token: String): DecodeIntegrityTokenResponse? {
        integrityTokenRequest.setIntegrityToken(token)

        val playIntegrity: PlayIntegrity =
            PlayIntegrity.Builder(httpTransport, jsonFactory, requestInitializer)
                .setApplicationName(applicationName.value)
                .setGoogleClientRequestInitializer(googleClientRequestInitializer)
                .build()

        val response = playIntegrity
            .v1()
            .decodeIntegrityToken(packageName.value, integrityTokenRequest)
            .execute()

        return response
    }
}