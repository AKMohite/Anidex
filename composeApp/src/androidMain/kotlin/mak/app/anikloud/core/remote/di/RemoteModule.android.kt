package mak.app.anikloud.core.remote.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getClientEngine(): HttpClientEngine = OkHttp.create()