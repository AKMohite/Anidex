package mak.app.anikloud.core.remote.utils

// TODO: This class can be domain model with constants?
internal object APIConstants {

    const val QUERY_STATUS = "filter[status]"
    const val QUERY_LIMIT = "page[limit]"
    const val QUERY_OFFSET = "page[offset]"
    const val QUERY_SORT = "sort"
    const val QUERY_INCLUDE = "include"

    const val QUERY_DATA_LIMIT = 20
    const val QUERY_CURRENT = "current"
    const val QUERY_SORT_USER_COUNT = "-userCount"
    const val QUERY_INCLUDE_GENRE = "categories"


    private fun filterQuery(param: String) =  "filter[$param]"
}