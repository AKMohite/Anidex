package mak.app.anikloud.core.remote.utils

internal object APIConstants {

    const val QUERY_STATUS = "filter[status]"
    const val QUERY_LIMIT = "page[limit]"
    const val QUERY_OFFSET = "page[offset]"
    const val QUERY_SORT = "sort"

    const val QUERY_DATA_LIMIT = 20
    const val QUERY_CURRENT = "current"
    const val QUERY_SORT_USER_COUNT = "-userCount"


    private fun filterQuery(param: String) =  "filter[$param]"
}