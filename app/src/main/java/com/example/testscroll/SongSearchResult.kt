package com.example.testscroll

import com.google.gson.annotations.SerializedName

data class SongSearchResult (
    @SerializedName("resultCount")
    val resultCount: Int,

    @SerializedName("results")
    val results: ArrayList<Song>
)