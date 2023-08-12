package com.prerak.galleryandroid.ui.theme

import android.media.Image
import android.media.ImageReader
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prerak.galleryandroid.MonthlyPhotos
import com.prerak.galleryandroid.R
import kotlin.random.Random



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForLibrary(photo : PhotosData, filterAccordingTo : Int) {
    Card(
        shape = if(filterAccordingTo !=4){ RoundedCornerShape(15.dp) }
        else RoundedCornerShape(7.dp)
            ,
        modifier = when (filterAccordingTo) {
            1 -> Modifier
                .height(250.dp)
                .width(300.dp)

            2 -> Modifier
                .height(290.dp)
                .width(350.dp)

            3 -> Modifier
                .height(320.dp)
                .width(400.dp)

            else -> {
                Modifier.padding(2.dp)
            }

        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        )
    {
        Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        {
            Image(painter = photo.imageResource,
                contentDescription = "",
                Modifier
                    .scale(1.6f)
                    .fillMaxWidth()
                    .fillMaxHeight())


        }
    }
}


@Composable
fun DemoPhotos() :List<PhotosData> {

    val listOfPhotos = mutableListOf<PhotosData>()
    repeat(30)
    {
        val x = PhotosData(imageResource = painterResource(id = R.drawable.scene3),
            date = Date(month = Random.nextInt(1,12) ,
                day  = Random.nextInt(1,30),
                year =Random.nextInt(2000,2025))
            )

        listOfPhotos.add(x)
    }
    repeat(30)
    {
        val x = PhotosData(imageResource = painterResource(id = R.drawable.scene1),
            date = Date(month = Random.nextInt(1,12) ,
                day  = Random.nextInt(1,30),
                year =Random.nextInt(2000,2025))
        )

        listOfPhotos.add(x)
    }
    repeat(30)
    {
        val x = PhotosData(imageResource = painterResource(id = R.drawable.scene2),
            date = Date(month = Random.nextInt(1,12) ,
                day  = Random.nextInt(1,30),
                year =Random.nextInt(2000,2025))
        )

        listOfPhotos.add(x)
    }
    return listOfPhotos.toList()
}


