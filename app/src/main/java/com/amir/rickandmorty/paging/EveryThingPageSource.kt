package com.amir.rickandmorty.paging


//class EveryThingPageSource(private val service: RickAndMortyApiService, private val query: String): PagingSource<Int, Character>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
//            if (query.isEmpty()){
//                return LoadResult.Page(emptyList(),prevKey = null,nextKey = null)
//            }
//
//        val page: Int = params.key ?: 1
//        val pageSize: Int = params.loadSize
//
//        val response = service.fetchCharacters(page)
//    }
//}