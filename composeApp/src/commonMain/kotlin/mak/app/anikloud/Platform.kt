package mak.app.anikloud

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform