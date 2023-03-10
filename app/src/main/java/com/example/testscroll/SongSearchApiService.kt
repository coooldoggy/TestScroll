package com.example.testscroll

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongSearchApiService {

    @GET("search")
    suspend fun querySong(
        @Query("term") term: String = "oasis",
        @Query("entity") entity: String = "song",
        @Query("offset") offset: Int = 0
    ): Response<SongSearchResult>
}