package mak.app.anikloud.core.common.model

//data class AppVault(
//    val apiHost: String = "",
//    val baseUrlExt: String = "",
//    val apiKey: String = "",
//    val contentType: String = "",
//    val clientId: String = "",
//    val clientSecret: String = ""
//)

object AppVault {

    fun loadSecrets(
        apiHost: String,
        baseUrlExt: String,
        apiKey: String,
        contentType: String,
        clientId: String,
        clientSecret: String
    ): AppVault {
        this.apiHost = apiHost
        this.baseUrlExt = baseUrlExt
        this.apiKey = apiKey
        this.contentType = contentType
        this.clientId = clientId
        this.clientSecret = clientSecret
        return this
    }

    var apiHost: String = ""
    var baseUrlExt: String = ""
    var apiKey: String = ""
    var contentType: String = ""
    var clientId: String = ""
    var clientSecret: String = ""
}