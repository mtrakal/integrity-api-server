package cz.mtrakal

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.playintegrity.v1.PlayIntegrityRequestInitializer
import com.google.api.services.playintegrity.v1.model.DecodeIntegrityTokenRequest
import com.google.auth.Credentials
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import cz.mtrakal.integrity.ApplicationName
import cz.mtrakal.integrity.DecodeTokenUseCase
import cz.mtrakal.integrity.DecodeTokenUseCaseImpl
import cz.mtrakal.integrity.PackageName
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.FileInputStream

val koinModules: Module = module {
    single<DecodeTokenUseCase> {
        DecodeTokenUseCaseImpl(get(), get(), get(), get(), get(), get(), get())
    }
    single<HttpTransport> { NetHttpTransport() }
    single<JsonFactory> { GsonFactory() }
    factory<GoogleClientRequestInitializer> { PlayIntegrityRequestInitializer(); }
    single<ApplicationName> { ApplicationName(Config.APPLICATION_NAME) }
    single<PackageName> { PackageName(Config.PACKAGE_NAME) }
    factory<DecodeIntegrityTokenRequest> { DecodeIntegrityTokenRequest() }
    single<Credentials> { GoogleCredentials.fromStream(FileInputStream(Config.SERVICE_ACCOUNT_FILE_PATH)) }
    factory<HttpRequestInitializer> { HttpCredentialsAdapter(get()) }
}