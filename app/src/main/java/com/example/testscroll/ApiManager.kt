package com.example.testscroll

import retrofit2.Response

object ApiManager {
    private val songSearchApiService by lazy {
        NetworkManager().createService(SongSearchApiService::class.java)
    }

    suspend fun queryDaySongs(offset: Int) : Response<SongSearchResult> {
        return songSearchApiService.querySong(offset = offset)
    }
}