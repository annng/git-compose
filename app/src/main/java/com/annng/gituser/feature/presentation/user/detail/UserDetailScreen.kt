package com.annng.gituser.feature.presentation.user.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.annng.gituser.feature.presentation.user.detail.component.ImageParallax
import com.annng.gituser.feature.presentation.user.detail.component.ItemFollower
import com.annng.gituser.ui.widget.AppBar
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun UserDetailScreen(
    navController : NavController,
    username : String,
    viewModel: UserDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val stateFollower = viewModel.followerState.value
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = username, block = {
        viewModel.getDetailUser(username)
        viewModel.getFollowerUser(username)
    })

    Scaffold(
        topBar =  { AppBar(title = "Detail User", navController = navController)}
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
//            ImageParallax(urlImage = state.user?.html_url ?: "-")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.user?.html_url ?: "-")
            Spacer(modifier = Modifier.height(16.dp))

            Column {
                val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 3
                Text(text = "Followers", style = MaterialTheme.typography.h2)
                com.google.accompanist.flowlayout.FlowRow(mainAxisSize = SizeMode.Expand, mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly) {
                    stateFollower.users?.forEachIndexed { index, user ->
                        ItemFollower(user, itemSize)
                    }
                }
            }

        }
    }
}