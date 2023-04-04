package com.annng.gituser.feature.presentation.user.list.component

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.util.CoilUtils
import com.annng.gituser.domain.model.User
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient

@Composable
fun ItemUser(user: User) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
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