package com.prerak.galleryandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.prerak.galleryandroid.ui.theme.BottomNavigator
import com.prerak.galleryandroid.ui.theme.DemoPhotos
import com.prerak.galleryandroid.ui.theme.ForYouView
import com.prerak.galleryandroid.ui.theme.GalleryAndroidTheme
import com.prerak.galleryandroid.ui.theme.ProductsDataBase
import com.prerak.galleryandroid.ui.theme.ProductsUiState
import com.prerak.galleryandroid.ui.theme.ProductsViewModel
import com.prerak.galleryandroid.ui.theme.StateFullScreen
import com.prerak.galleryandroid.ui.theme.StateLessScreen
import kotlin.random.Random

class MainActivity : ComponentActivity() {


    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ProductsDataBase::class.java,
            "Products.db"
        ).build()
    }

    private val viewModel by viewModels<ProductsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProductsViewModel(db.dao) as T
                }
            }
        }
    )


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
                       .weight(16f)
                       .background(color = Color(0XFFF2F3F4)),
                       contentAlignment = Alignment.Center) {
                      //Conditional TopBox
                       when(activeBadge.value ){
                           1 -> LibraryView( DemoPhotos())
                           2 -> ForYouView(DemoPhotos())
                           3-> {
                               val productUiState by viewModel.productsUiState.collectAsState()
                               StateLessScreen(productUiState)
                           }
                           else->{}

                       }

                    }
                    //BottomNavigatorBadge
                   Box(modifier = Modifier.weight(1f)){ BottomNavigator(activeBadge.value) { activeBadge.value = it } }
                }
            }
        }
    }
}


