package com.annng.gituser.feature.presentation.user.detail.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.annng.gituser.R
import com.annng.gituser.domain.model.User
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ItemFollower(user : User, onClickable : (User) -> Unit) {
    Box (modifier = Modifier.padding(16.dp)){
        Card(modifier = Modifier.clickable { onClickable(user) }) {
            Column(
                modifier = Modifier
                    .padding(8.dp).fillMaxWidth()
            ) {
                CoilImage(
                    imageModel = { user.avatar_url },
                    modifier = Modifier
                        .height(126.dp)
                        .fillMaxWidth(),// loading a network image or local resource using an URL.
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    failure = {
                        CoilImage(
                            imageModel = { R.mipmap.ic_launcher },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            ),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.login, style = MaterialTheme.typography.h2)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.url, style = MaterialTheme.typography.body1)
            }
        }
    }
}