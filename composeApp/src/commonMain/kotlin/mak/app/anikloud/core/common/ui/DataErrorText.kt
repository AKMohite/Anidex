package mak.app.anikloud.core.common.ui

import anikloud.composeapp.generated.resources.Res
import anikloud.composeapp.generated.resources.error_no_internet
import anikloud.composeapp.generated.resources.error_request_timeout
import anikloud.composeapp.generated.resources.error_serialization
import anikloud.composeapp.generated.resources.error_too_many_requests
import anikloud.composeapp.generated.resources.error_unknown
import mak.app.anikloud.core.common.model.DataError
import mak.app.anikloud.core.remote.ext.RemoteException

fun Throwable.toUiText(): UiText {
    return if (this is RemoteException) {
        val stringRes = when(this.type) {
//            DataError.Local.DISK_FULL -> Res.string.error_disk_full
//            DataError.Local.UNKNOWN -> Res.string.error_unknown
            DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
            DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
            DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
            DataError.Remote.SERVER -> Res.string.error_unknown
            DataError.Remote.SERIALIZATION -> Res.string.error_serialization
            DataError.Remote.UNKNOWN -> Res.string.error_unknown
        }
        UiText.StringResourceId(stringRes)
    } else {
        UiText.StringResourceId(Res.string.error_unknown)
    }
}

//fun DataError.toUiText(): UiText {
//    val stringRes = when(this) {
//        DataError.Local.DISK_FULL -> Res.string.error_disk_full
//        DataError.Local.UNKNOWN -> Res.string.error_unknown
//        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
//        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
//        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
//        DataError.Remote.SERVER -> Res.string.error_unknown
//        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
//        DataError.Remote.UNKNOWN -> Res.string.error_unknown
//    }
//
//    return UiText.StringResourceId(stringRes)
//}