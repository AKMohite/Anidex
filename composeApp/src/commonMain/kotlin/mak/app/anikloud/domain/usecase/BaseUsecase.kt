package mak.app.anikloud.domain.usecase

internal interface BaseUsecase<P, R> {
    suspend operator fun invoke(params: P): R
}