package com.annng.gituser.feature.presentation.user.detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.annng.gituser.ui.theme.Purple200
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    viewModel: UserDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar =  { TopAppBar(title = { Text(text = state.users?.login ?: "")}, backgroundColor = Purple200)}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CoilImage(
                imageModel = { state.users?.avatar_url },
                modifier = Modifier
                    .height(126.dp)
                    .width(76.dp),// loading a network image or local resource using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                failure = {
                    Log.e("error", it.reason.toString())
                }
            )
            Text(text = state.users?.html_url ?: "-")
        }
    }
}