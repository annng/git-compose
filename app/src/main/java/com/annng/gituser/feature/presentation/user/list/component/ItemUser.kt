package com.annng.gituser.feature.presentation.user.list.component

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.annng.gituser.domain.model.User

@Composable
fun ItemUser(user: User) {
    Card(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
       Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
           Image(
               modifier = Modifier
                   .height(160.dp)
                   .width(75.dp)
                   .aspectRatio(1f, matchHeightConstraintsFirst = true)
                   .clip(RoundedCornerShape(10.dp))
                   .border(width = 20.dp, shape = CircleShape, color = Color.Transparent),
               contentScale = ContentScale.Crop,
               painter = rememberAsyncImagePainter(user.avatar_url),
               contentDescription = null
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