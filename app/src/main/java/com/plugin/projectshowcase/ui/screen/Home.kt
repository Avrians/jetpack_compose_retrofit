package com.plugin.projectshowcase.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.plugin.projectshowcase.data.api.model.Photos
import com.plugin.projectshowcase.viewModel.HomeViewModel

@Composable
fun Home () {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by homeViewModel.state.collectAsState()

    LazyColumn(){
        if (state.isEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }
         items(state) { photos : Photos ->
             PhotosListItem(photos = photos)
         }
    }
}

@Composable
fun PhotosListItem( photos : Photos ) {
    val imagePainter = rememberImagePainter(data = photos.thumbnailUrl)

    Card(shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)) {
        Box {
           Image(painter = imagePainter,
               contentDescription = "image",
           modifier = Modifier
               .fillMaxWidth()
               .height(150.dp),
               contentScale = ContentScale.FillBounds
           )
            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)) {
                    Text(text = photos.title)
                }
            }
        }
    }
}


//fun PreviewListItem() {
//    Surfacxe(modifier = Modifier.fillMaxSize()) {
//
//    }
//}
