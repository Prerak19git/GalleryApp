package com.prerak.galleryandroid

import android.widget.Space
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prerak.galleryandroid.ui.theme.CardForLibrary

import com.prerak.galleryandroid.ui.theme.PhotosData
import java.time.Month

import kotlin.random.Random




@Composable
fun LibraryView(listOfPhotos : List<PhotosData>) {

    val filterAccordingTo  = remember {
        mutableStateOf(1)
    }
   val listOfPhotosSorted = listOfPhotos.sortedWith(compareBy<PhotosData> { it.date.year }.thenBy { it.date.month }.thenBy { it.date.day }).toList()


    Box (modifier = Modifier.fillMaxSize()) {

       when(filterAccordingTo.value) {
           1 -> YearlyPhotos(listOfPhotos = listOfPhotosSorted)
           2->  MonthlyPhotos(listOfPhotos = listOfPhotosSorted)
           3-> DailyPhotos(listOfPhotos = listOfPhotosSorted)
           else ->{
               AllPhotos(listOfPhotos = listOfPhotosSorted)
           }

       }

       // The filters for photos
       Row(modifier = Modifier
           .padding(2.dp)
           .fillMaxWidth()
           .background(
               shape = RoundedCornerShape(40.dp), color = Color.LightGray
           )
           .align(BottomCenter),
           horizontalArrangement = Arrangement.SpaceAround,){
           PhotosByDurationBar(filterAccordingTo = filterAccordingTo.value
           ) { filterAccordingTo.value = it }
       }
    }
}

@Composable
fun YearlyPhotos(listOfPhotos : List<PhotosData>) {


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(listOfPhotos.size)
        {
            if( it == 0 || listOfPhotos[it].date.year > listOfPhotos[it-1].date.year)
            {   val year = listOfPhotos[it].date.year

                Box(modifier = Modifier.fillMaxSize(), contentAlignment =  Center){
                    CardForLibrary(photo = listOfPhotos[it], 1)

                        Text(
                            text = "$year"  ,
                            fontSize = 32.sp,
                            modifier = Modifier.align(TopStart).offset(65.dp,y = 10.dp),
                          style = TextStyle(color = Color(0xFFEBECF0), fontWeight = FontWeight.W800,
                              fontFamily = FontFamily.SansSerif
                          )
                        )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}

@Composable
fun MonthlyPhotos(listOfPhotos : List<PhotosData>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(listOfPhotos.size)
        {
            if(it ==0 ||
                ((listOfPhotos[it].date.year >= listOfPhotos[it-1].date.year)&&
                        (listOfPhotos[it].date.month >= listOfPhotos[it-1].date.month))
                )
            {

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically){

                    Spacer(modifier = Modifier.width(50.dp))
                    Text(
                        text = "${Month.of(listOfPhotos[it].date.month)
                            .toString().lowercase().capitalize()} ",
                        fontSize = 26.sp,
                        modifier = Modifier,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "${listOfPhotos[it].date.year}",
                        fontSize = 26.sp,
                        modifier = Modifier,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray
                    )

                    Icon(painter = painterResource(id = R.drawable.baseline_navigate_next),
                        contentDescription = "" ,
                        tint = Color.LightGray,
                        modifier = Modifier.size(40.dp))


                }

                Spacer(modifier = Modifier.height(15.dp))

                Box{
                    CardForLibrary(photo = listOfPhotos[it], 2)
                }
                Spacer(modifier = Modifier.height(35.dp))
            }

        }
    }
}

@Composable
fun DailyPhotos(listOfPhotos : List<PhotosData>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(listOfPhotos.size)
        {
            if((it ==0 )||
                (listOfPhotos[it].date.year >= listOfPhotos[it-1].date.year)&& (listOfPhotos[it].date.month >= listOfPhotos[it-1].date.month)&& (listOfPhotos[it].date.day > listOfPhotos[it-1].date.day)
            )
            {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    contentAlignment = Center) {

                    CardForLibrary(photo = listOfPhotos[it], 3)
                    Text(
                        text = Month.of(listOfPhotos[it].date.month).toString()
                                 .lowercase().capitalize() +
                                " ${listOfPhotos[it].date.day}, " +
                                " ${listOfPhotos[it].date.year} ",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(TopEnd)
                            .offset(
                                x = (-30).dp,
                                y = 7.dp
                            ),
                        style = TextStyle(color = Color(0xFFDBDEE3), fontWeight = FontWeight.W800,
                            fontFamily = FontFamily.SansSerif
                        )

                    )
                }


                Spacer(modifier = Modifier.height(16.dp))
            }


        }
    }
}

@Composable
fun AllPhotos(listOfPhotos : List<PhotosData>) {

    LazyVerticalGrid(
       columns = GridCells.Adaptive(100.dp)
    ) {

        items(listOfPhotos.size)
        {
            Box{
                CardForLibrary(photo = listOfPhotos[it], 4)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
