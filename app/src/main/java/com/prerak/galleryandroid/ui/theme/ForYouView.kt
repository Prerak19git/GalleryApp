package com.prerak.galleryandroid.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prerak.galleryandroid.R
import kotlin.random.Random


@Composable
fun ForYouView( listOfPhotos : List<PhotosData> ) {

    val listOfPhotosSorted = listOfPhotos.sortedWith(compareBy<PhotosData> { it.date.year }.thenBy { it.date.month }.thenBy { it.date.day }).toList()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFE8E9EB)), contentAlignment = Alignment.Center) {
        LazyColumn(modifier = Modifier.fillMaxSize())
        {

            item{
                Spacer(modifier = Modifier.size(30.dp))
               Text(
                   modifier = Modifier
                       .fillMaxWidth()
                       .offset(x = 20.dp),
                   text = "For You",
                   style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Left)
                   )
                Spacer(modifier = Modifier.size(14.dp))
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.offset(x = 20.dp))
            }

            item{
                Spacer(modifier = Modifier.size(30.dp))
                Row(modifier = Modifier.fillMaxWidth())
                {
                    Text(
                        modifier = Modifier.offset(x =  20.dp),
                        text = "Memories",
                        style = TextStyle(fontSize = 22.sp , fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Left)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-20).dp),
                        text = "See All",
                        style = TextStyle(fontSize = 18.sp , fontWeight = FontWeight.Light, textAlign = TextAlign.Right,
                            color = Color.Blue)
                    )

                }

            }

            item{
                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier =Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

                    LazyRow(modifier = Modifier.fillMaxSize()) {
                        items(listOfPhotosSorted.size) {
                            Spacer(modifier = Modifier.width(15.dp))
                            ForYouCard(photo = listOfPhotosSorted[it])

                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ForYouCard(photo : PhotosData ) {

    val isFavourite = remember{ mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(450.dp)
            .width(375.dp)

        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        ) {
        Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(painter = photo.imageResource,
                contentDescription = "",
                Modifier
                    .scale(2.5f)
                    .fillMaxWidth()
                    .fillMaxHeight())

           Box(modifier = Modifier.fillMaxSize()) {
                IconButton(onClick = { isFavourite.value = !isFavourite.value},
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopEnd)
                        .offset(x = (-10).dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 10.dp)
                        .align(Alignment.TopCenter),
                        horizontalArrangement = Arrangement.End){
                        Icon(
                            painter = painterResource(
                                id = if (isFavourite.value) {
                                    R.drawable.heart
                                } else R.drawable.emptyheart
                            ), contentDescription = "",
                            modifier = Modifier.scale(1.5f),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(15.dp))

                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_format_list_bulleted_24
                            ), contentDescription = "",
                            modifier = Modifier.scale(1.5f),
                            tint = Color.White
                        )
                    }
                }
            }

        }
    }
}


