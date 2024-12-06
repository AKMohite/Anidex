package mak.app.anikloud.core.remote.ext

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.io.IOException
import mak.app.anikloud.core.common.model.AppResult
import mak.app.anikloud.core.common.model.DataError
import kotlin.coroutines.coroutineContext

class RemoteException(
    val type: DataError.Remote,
    val msg: String? = null,
    val statusCode: Int? = null,
    val throwable: Throwable? = null
): RuntimeException(throwable?.message ?: msg)

suspend inline fun <reified T> safeKtorCall(execute: () -> HttpResponse): T {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        throw RemoteException(type = DataError.Remote.REQUEST_TIMEOUT, throwable = e)
    } catch (e: UnresolvedAddressException) {
        throw RemoteException(type = DataError.Remote.NO_INTERNET, throwable = e)
    } catch (e: IOException) {
        throw RemoteException(type = DataError.Remote.NO_INTERNET, throwable = e)
    } catch (e: Exception) {
        println(e)
        coroutineContext.ensureActive() // coroutine cancellation exception
        throw RemoteException(type = DataError.Remote.UNKNOWN, throwable = e)
    }
    when(val statusCode = response.status.value) {
        in 200..299 -> {
            try {
                return response.body<T>()
            } catch (e: NoTransformationFoundException) {
                throw RemoteException(type = DataError.Remote.SERIALIZATION, throwable = e)
            }
        }
        408 -> throw RemoteException(type = DataError.Remote.REQUEST_TIMEOUT, statusCode = statusCode)
        429 -> throw RemoteException(type = DataError.Remote.TOO_MANY_REQUESTS, statusCode = statusCode)
        in 500..599 -> throw RemoteException(type = DataError.Remote.SERVER, statusCode = statusCode)
        else -> throw RemoteException(type = DataError.Remote.UNKNOWN, statusCode = statusCode)
    }
}


suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): AppResult<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return AppResult.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return AppResult.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive() // coroutine cancellation exception
        return AppResult.Error(DataError.Remote.UNKNOWN)
    }
    return responseToResult(response)
}

suspend inline fun <reified  T> responseToResult(
    response: HttpResponse
): AppResult<T, DataError.Remote> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                AppResult.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                AppResult.Error(DataError.Remote.SERIALIZATION)
            }
        }
        408 -> AppResult.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> AppResult.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> AppResult.Error(DataError.Remote.SERVER)
        else -> AppResult.Error(DataError.Remote.UNKNOWN)
    }
}