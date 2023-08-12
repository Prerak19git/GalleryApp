package com.prerak.galleryandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prerak.galleryandroid.ui.theme.GalleryAndroidTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAndroidTheme {

                // Remembering the state of BottomNavigator
                val activeBadge = remember {
                    mutableStateOf(1)
                }
                // The Screen
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally){

                   Box(modifier = Modifier
                       .height(720.dp)
                       .fillMaxWidth()
                       .background(color = Color(0XFFF2F3F4)),
                       contentAlignment = Alignment.Center) {
                      //Conditional TopBox
                       if(activeBadge.value ==1){ LibraryView(items = mutableListOf()) }

                    }
                    //BottomNavigatorBadge
                    BottomNavigator(activeBadge.value) { activeBadge.value = it }
                }
            }
        }
    }
}

@Composable
fun DemoCard() {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(250.dp)
            .width(300.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    )
    {
        Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        {
            Image(painter = painterResource(id =R.drawable.demoimage),
                contentDescription = "",
                Modifier
                    .scale(1.25f)
                    .fillMaxWidth()
                    .fillMaxHeight())

            Text(text = "This is just a demo text",
                modifier = Modifier.align(Alignment.BottomCenter),
                style = TextStyle(color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif)
            )
        }
    }
}


@Composable
fun BottomNavigator(activeBadge :Int, onBadgeChange : (optionChangesTo :Int)->Unit) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart){
        NavigationBar(containerColor = Color.Transparent,
            ) {

            NavigationBarItem(
                icon = {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)
                   {
                        Icon(
                            painter = painterResource(id = R.drawable.library),
                            contentDescription = "somthinf",
                            tint = if (activeBadge != 1) {
                                Color.DarkGray
                            } else Color(0xFF3A9DBC),
                            modifier = Modifier.scale(1.3f)
                        )
                       Text(text = "Library", color = if (activeBadge != 1) {
                           Color.DarkGray
                       } else Color(0xFF3A9DBC))
                    }
                }
                ,
                onClick = {onBadgeChange(1)},
                selected = false
            )
            NavigationBarItem(
                icon = {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.foryou),
                            contentDescription = "somthinf",
                            tint = if (activeBadge != 2) {
                                Color.DarkGray
                            } else Color(0xFF3A9DBC),
                            modifier = Modifier.scale(1.3f)
                        )
                        Text(text = "For You", color = if (activeBadge != 2) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC))
                    }
                }
                ,
                onClick = {onBadgeChange(2)},
                selected = false
            )
            NavigationBarItem(
                icon = {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.albums),
                            contentDescription = "somthinf",
                            tint = if (activeBadge != 3) {
                                Color.DarkGray
                            } else Color(0xFF3A9DBC),
                            modifier = Modifier.scale(1.3f)
                        )
                        Text(text = "Albums", color = if (activeBadge != 3) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC))
                    }
                }
                ,
                onClick = {onBadgeChange(3)},
                selected = false
            )
            NavigationBarItem(
                icon = {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "somthinf",
                            tint = if (activeBadge != 4) {
                                Color.DarkGray
                            } else Color(0xFF3A9DBC),
                            modifier = Modifier.scale(1.3f)
                        )
                        Text(text = "Search", color = if (activeBadge != 4) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC))
                    }
                }
                ,
                onClick = {onBadgeChange(4)},
                selected = false
            )
        }
    }

}

