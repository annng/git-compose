package com.annng.gituser.feature.presentation.user.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.annng.gituser.R
import com.annng.gituser.common.util.ext.pxToDp
import com.annng.gituser.common.util.ext.toPx
import com.annng.gituser.feature.presentation.user.detail.component.ImageParallax
import com.annng.gituser.feature.presentation.user.detail.component.ItemFollower
import com.annng.gituser.ui.theme.Overlay
import com.annng.gituser.ui.widget.AppBar
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserDetailScreen(
    navController: NavController,
    username: String,
    viewModel: UserDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val stateFollower = viewModel.followerState.value
    val scrollState = rememberScrollState()
    val offset = (scrollState.value / 3).coerceAtMost(120.dp.toPx().toInt())

    LaunchedEffect(key1 = username, block = {
        viewModel.getDetailUser(username)
        viewModel.getFollowerUser(username)
    })

    Scaffold(
        topBar = { AppBar(title = "Detail User", navController = navController) }
    ) { _ ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                ImageParallax(urlAvatar = state.user?.avatar_url ?: "-", offset = offset.pxToDp())
                Box(modifier = Modifier
                    .background(Overlay)
                    .fillMaxSize())
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = state.user?.login ?: "John Doe", style = MaterialTheme.typography.h4, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            FlowRow(maxItemsInEachRow = 3,
                content = {
                    stateFollower.users?.let { list ->
                        list.forEachIndexed { index, user ->
                            ItemFollower(user) {
                                viewModel.getDetailUser(user.login)
                                viewModel.getFollowerUser(user.login)
                            }
                        }
                    }
                })

        }
    }
}