package mak.app.anikloud.data.di

import mak.app.anikloud.data.di.DataQualifiers.AIRING_STORE
import mak.app.anikloud.data.di.DataQualifiers.GET_AIRING
import mak.app.anikloud.data.di.DataQualifiers.GET_BANNER
import mak.app.anikloud.data.di.DataQualifiers.GET_POPULAR
import mak.app.anikloud.data.di.DataQualifiers.GET_TOP_RATED
import mak.app.anikloud.data.di.DataQualifiers.GET_TRENDING
import mak.app.anikloud.data.di.DataQualifiers.GET_UPCOMING
import mak.app.anikloud.data.di.DataQualifiers.TOP_RATED_STORE
import mak.app.anikloud.data.di.DataQualifiers.POPULAR_STORE
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_AIRING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_POPULAR
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_TOP_RATED
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_TRENDING
import mak.app.anikloud.data.di.DataQualifiers.REFRESH_UPCOMING
import mak.app.anikloud.data.di.DataQualifiers.TRENDING_STORE
import mak.app.anikloud.data.di.DataQualifiers.UPCOMING_STORE
import mak.app.anikloud.data.repository.AppAnimeRepository
import mak.app.anikloud.data.store.AiringAnimeStore
import mak.app.anikloud.data.store.DiscoverAnimeStore
import mak.app.anikloud.data.store.TopRatedAnimeStore
import mak.app.anikloud.data.store.PopularAnimeStore
import mak.app.anikloud.data.store.TrendingAnimeStore
import mak.app.anikloud.data.store.UpcomingAnimeStore
import mak.app.anikloud.data.usecase.refresh.RefreshAiringAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetAiringAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetBannerUseCase
import mak.app.anikloud.data.usecase.fetch.GetPopularAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetTopRatedAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetTrendingAnimeUseCase
import mak.app.anikloud.data.usecase.fetch.GetUpcomingAnimeUseCase
import mak.app.anikloud.data.usecase.refresh.RefreshPopularAnimeUseCase
import mak.app.anikloud.data.usecase.refresh.RefreshTopRatedAnimeUseCase
import mak.app.anikloud.data.usecase.refresh.RefreshTrendingAnimeUseCase
import mak.app.anikloud.data.usecase.refresh.RefreshUpcomingAnimeUseCase
import mak.app.anikloud.domain.repository.AnimeRepository
import mak.app.anikloud.domain.usecase.GetAnimeUsecase
import mak.app.anikloud.domain.usecase.RefreshAnimeUsecase
import org.koin.dsl.module

val dataModule = module {

    // region store5
//    factory(qualifier = "qualifier-name") {}
    factory<DiscoverAnimeStore>(qualifier = AIRING_STORE) { AiringAnimeStore(
        api = get(),
        animeDAO = get(),
        airingAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner = get(),
        dispatcher = get()
    ) }

    factory<DiscoverAnimeStore>(qualifier = TRENDING_STORE) { TrendingAnimeStore(
        api = get(),
        animeDAO = get(),
        trendingAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner = get(),
        dispatcher = get()
    ) }
    factory<DiscoverAnimeStore>(qualifier = POPULAR_STORE) { PopularAnimeStore(
        api = get(),
        animeDAO = get(),
        popularAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner = get(),
        dispatcher = get()
    ) }

    factory<DiscoverAnimeStore>(qualifier = TOP_RATED_STORE) { TopRatedAnimeStore(
        api = get(),
        animeDAO = get(),
        topRatedAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner = get(),
        dispatcher = get()
    ) }

    factory<DiscoverAnimeStore>(qualifier = UPCOMING_STORE) { UpcomingAnimeStore(
        api = get(),
        animeDAO = get(),
        upcomingAnimeDAO = get(),
        lastSyncDao = get(),
        transactionRunner = get(),
        dispatcher = get()
    ) }
    // endregion

//    TODO: usecase must be domain and not data?
    // region usecase
    factory<GetAnimeUsecase>(qualifier = GET_BANNER) { GetBannerUseCase(get()) }
    factory<GetAnimeUsecase>(qualifier = GET_AIRING) { GetAiringAnimeUseCase(get()) }
    factory<GetAnimeUsecase>(qualifier = GET_POPULAR) { GetPopularAnimeUseCase(get()) }
    factory<GetAnimeUsecase>(qualifier = GET_TOP_RATED) { GetTopRatedAnimeUseCase(get()) }
    factory<GetAnimeUsecase>(qualifier = GET_UPCOMING) { GetUpcomingAnimeUseCase(get()) }
    factory<GetAnimeUsecase>(qualifier = GET_TRENDING) { GetTrendingAnimeUseCase(get()) }
    factory<RefreshAnimeUsecase>(qualifier = REFRESH_AIRING) { RefreshAiringAnimeUseCase(get(AIRING_STORE)) }
    factory<RefreshAnimeUsecase>(qualifier = REFRESH_POPULAR) { RefreshPopularAnimeUseCase(get(POPULAR_STORE)) }
    factory<RefreshAnimeUsecase>(qualifier = REFRESH_UPCOMING) { RefreshUpcomingAnimeUseCase(get(UPCOMING_STORE)) }
    factory<RefreshAnimeUsecase>(qualifier = REFRESH_TOP_RATED) { RefreshTopRatedAnimeUseCase(get(TOP_RATED_STORE)) }
    factory<RefreshAnimeUsecase>(qualifier = REFRESH_TRENDING) { RefreshTrendingAnimeUseCase(get(TRENDING_STORE)) }
    // endregion

    single<AnimeRepository> { AppAnimeRepository(api = get(), dao = get(), transactionRunner = get()) }
}