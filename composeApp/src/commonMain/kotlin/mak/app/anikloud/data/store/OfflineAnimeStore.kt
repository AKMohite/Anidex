package mak.app.anikloud.data.store

import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mak.app.anikloud.core.common.util.Dispatcher
import mak.app.anikloud.core.database.DatabaseTransactionRunner
import mak.app.anikloud.core.database.LastSyncDAO
import mak.app.anikloud.core.database.dao.AnimeDAO
import mak.app.anikloud.core.database.dao.EntityDAO
import mak.app.anikloud.core.remote.dto.DataDTO
import mak.app.anikloud.core.remote.dto.DiscoverAnimeDTO
import mak.app.anikloud.data.toEntities
import mak.app.anikloud.data.toModels
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.domain.model.SyncRequest
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.Validator
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

internal abstract class OfflineAnimeStore<Entity>(
    private val animeDAO: AnimeDAO,
    private val tableDAO: EntityDAO<Entity>,
    private val lastSyncDao: LastSyncDAO,
    private val transactionRunner: DatabaseTransactionRunner,
    private val dispatcher: Dispatcher,
    override val syncRequest: SyncRequest
): DiscoverAnimeStore {

    override operator fun invoke(): Store<Int, List<Anime>> = storeBuilder(
        fetcher = Fetcher.of { page: Int ->
            withContext(dispatcher.io) {
                apiCall(page)
            }
        },
        sourceOfTruth = SourceOfTruth.of<Int, DiscoverAnimeDTO, List<Anime>>(
            reader = { page ->
                tableDAO.entriesObservable(page)
                    .map {
                        it.toModels()
                    }.flowOn(dispatcher.computation)
            },
            writer = { page, dto ->
                transactionRunner {
                    val entities = mapEntities(page, dto.data)
                    if (page == 1) {
                        tableDAO.deleteAll()
                        tableDAO.insert(entities)
                        lastSyncDao.insert(syncRequest)
                    } else {
                        tableDAO.updatePage(page, entities)
                    }
                    animeDAO.insert(dto.data.toEntities())
                }
            },
            delete = tableDAO::deletePage,
            deleteAll = tableDAO::deleteAll
        ).usingDispatchers(
            readDispatcher = dispatcher.io,
            writeDispatcher = dispatcher.io
        )
    ).validator(
        Validator.by { result ->
            withContext(dispatcher.io) {
                lastSyncDao.isRequestValid(
                    requestType = syncRequest,
                    threshold = if (result.isNotEmpty()) 3.hours else 30.minutes,
                )
            }
        }
    ).build()

    abstract fun mapEntities(page: Int, data: List<DataDTO>): List<Entity>
}

internal interface DiscoverAnimeStore {
    val syncRequest: SyncRequest

    operator fun invoke(): Store<Int, List<Anime>>

    suspend fun apiCall(page: Int): DiscoverAnimeDTO
}
