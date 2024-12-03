package mak.app.anikloud.core.remote.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mak.app.anikloud.core.common.model.AppVault
import mak.app.anikloud.core.remote.AnimeAPI
import mak.app.anikloud.core.remote.KtorAnimeAPI
import org.koin.dsl.module

val remoteModule = module {
    single<HttpClient> { getHttpClient(engine = get(), vault = get()) }
    single<AnimeAPI> { KtorAnimeAPI(client = get(), vault = get()) }
}

private fun getHttpClient(engine: HttpClientEngine, vault: AppVault): HttpClient {
    return HttpClient(engine = engine) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 20_000L
            requestTimeoutMillis = 20_000L
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.ALL
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = vault.apiHost
                header("API", vault.apiKey)
            }
            accept(ContentType(contentType = "application", contentSubtype = vault.contentType))
            contentType(ContentType(contentType = "application", contentSubtype = vault.contentType))
//            contentType(ContentType.Application.HalJson) must use the 'application/vnd.api+json'
        }
    }
}
