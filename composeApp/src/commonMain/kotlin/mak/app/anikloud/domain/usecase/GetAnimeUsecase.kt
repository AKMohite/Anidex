package mak.app.anikloud.domain.usecase

internal interface GetAnimeUsecase<P, R> {
    operator fun invoke(params: P): R
}