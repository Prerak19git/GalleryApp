package com.prerak.galleryandroid.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.prerak.galleryandroid.R

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
                            modifier = Modifier.scale(1f)
                        )
                        Text(text = "Library", color = if (activeBadge != 1) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC)
                        )
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
                            modifier = Modifier.scale(1f)
                        )
                        Text(text = "For You", color = if (activeBadge != 2) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC)
                        )
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
                            painter = painterResource(id = R.drawable.baseline_shopping_bag_24),
                            contentDescription = "shop",
                            tint = if (activeBadge != 3) {
                                Color.DarkGray
                            } else Color(0xFF3A9DBC),
                            modifier = Modifier.scale(1f)
                        )
                        Text(text = "Shop", color = if (activeBadge != 3) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC)
                        )
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
                            modifier = Modifier.scale(1f)
                        )
                        Text(text = "Search", color = if (activeBadge != 4) {
                            Color.DarkGray
                        } else Color(0xFF3A9DBC)
                        )
                    }
                }
                ,
                onClick = {onBadgeChange(4)},
                selected = false
            )
        }
    }

}

