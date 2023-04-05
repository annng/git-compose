package com.annng.gituser.feature.presentation.user.list.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.annng.gituser.domain.model.User
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ItemUser(user: User, onItemClick: (User) -> Unit) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(16.dp)
        .clickable { onItemClick(user) }
        .fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = 2.dp) {
       Row(modifier = Modifier
           .padding(16.dp)
           .fillMaxWidth()) {
           Spacer(modifier = Modifier.width(8.dp))
           CoilImage(
               imageModel = { user.avatar_url },
               modifier = Modifier.height(126.dp).width(76.dp),// loading a network image or local resource using an URL.
               imageOptions = ImageOptions(
                   contentScale = ContentScale.Crop,
                   alignment = Alignment.Center
               ),
               failure = {
                   Log.e("error", it.reason.toString())
               }
           )
           Spacer(modifier = Modifier.width(16.dp))
           Column(modifier = Modifier.fillMaxWidth()) {
               Text(text = user.login, fontSize = 16.sp, fontWeight = FontWeight.Bold)
               Spacer(modifier = Modifier.height(8.dp))
               Text(text = "Follower : ${user.followers_url}", fontSize = 14.sp, fontWeight = FontWeight.Normal)
               Spacer(modifier = Modifier.height(8.dp))
               Text(text = "Following : ${user.following_url}", fontSize = 14.sp, fontWeight = FontWeight.Normal)
           }
       } 
    }
}