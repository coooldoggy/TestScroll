package com.example.testscroll

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SongListScreen(viewModel: MainViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        viewModel.songListState.forEach { _song ->
            item {
                SongItem(song = _song)
            }
        }

        item {
            if (viewModel.uiState is UiState.UiEvent.Progress) {
                ProgressBarItem()
                viewModel.loadData()
            }
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier.size(100.dp).padding(10.dp),
            model = song.artworkUrl60,
            contentDescription = "artistPicture",
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = song.trackName)
            Text(text = song.collectionName)
            Text(text = song.artistName)
        }
    }
}

@Composable
fun ProgressBarItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp,
                bottom = 30.dp,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            color = Color.Black,
        )
    }
}
