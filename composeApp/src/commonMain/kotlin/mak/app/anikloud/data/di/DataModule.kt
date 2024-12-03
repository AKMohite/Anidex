package mak.app.anikloud.data.di

import mak.app.anikloud.data.repository.AppAnimeRepository
import mak.app.anikloud.domain.repository.AnimeRepository
import org.koin.dsl.module

val dataModule = module {
    single<AnimeRepository> { AppAnimeRepository(api = get(), dao = get(), transactionRunner = get()) }
}