package mak.app.anikloud.data.di

import mak.app.anikloud.data.di.DataQualifiers.AIRING_STORE
import mak.app.anikloud.data.di.DataQualifiers.GET_AIRING
import mak.app.anikloud.data.di.DataQualifiers.GET_BANNER
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_AIRING
import mak.app.anikloud.data.di.DataQualifiers.TRENDING_STORE
import mak.app.anikloud.data.repository.AppAnimeRepository
import mak.app.anikloud.data.store.AiringAnimeStore
import mak.app.anikloud.data.store.DiscoverAnimeStore
import mak.app.anikloud.data.store.TrendingAnimeStore
import mak.app.anikloud.data.usecase.refresh.RefreshAiringAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetAiringAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetBannerUseCase
import mak.app.anikloud.domain.repository.AnimeRepository
import org.koin.dsl.module

val dataModule = module {

    // region store5
//    factory(qualifier = "qualifier-name") {}
    factory<DiscoverAnimeStore>(qualifier = AIRING_STORE) { AiringAnimeStore(
        api = get(),
        animeDAO = get(),
        airingAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner =get(),
        dispatcher = get()
    ) }

    factory<DiscoverAnimeStore>(qualifier = TRENDING_STORE) { TrendingAnimeStore(
        api = get(),
        animeDAO = get(),
        trendingAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner =get(),
        dispatcher = get()
    ) }
    // endregion

//    TODO: usecase must be domain and not data?
    // region usecase
    factory(qualifier = GET_BANNER) { GetBannerUseCase(get()) }
    factory(qualifier = GET_AIRING) { GetAiringAnimeUseCase(get()) }
    factory(qualifier = REFRESH_AIRING) { RefreshAiringAnimeUseCase(get(AIRING_STORE)) }
    // endregion

    single<AnimeRepository> { AppAnimeRepository(api = get(), dao = get(), transactionRunner = get()) }
}