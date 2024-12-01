package mak.app.anikloud.core.common.ui

import anikloud.composeapp.generated.resources.Res
import anikloud.composeapp.generated.resources.error_disk_full
import anikloud.composeapp.generated.resources.error_no_internet
import anikloud.composeapp.generated.resources.error_request_timeout
import anikloud.composeapp.generated.resources.error_serialization
import anikloud.composeapp.generated.resources.error_too_many_requests
import anikloud.composeapp.generated.resources.error_unknown
import mak.app.anikloud.core.common.model.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}