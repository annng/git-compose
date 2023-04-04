package com.annng.gituser.feature.presentation.user.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.annng.gituser.feature.presentation.user.list.component.ItemUser
import com.annng.gituser.ui.theme.Black
import com.annng.gituser.ui.theme.Purple200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: UserListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Github User") }, backgroundColor = Purple200)}
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    state.users?.let {
                        items(it) {
                            ItemUser(user = it)
                        }
                    }

                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (state.error.isNotBlank()){
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = state.error,
                        color = Black,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                    Button(onClick = {
                        viewModel.getUsers()
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Purple200)) {
                        Text(text = "Retry", color = Black)
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    UserListScreen(rememberNavController())
}