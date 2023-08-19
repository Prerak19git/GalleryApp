package com.prerak.galleryandroid.ui.theme

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.prerak.galleryandroid.R


@Composable
fun StateLessScreen(
    productsUiState: ProductsUiState,

) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        contentAlignment = Alignment.Center)
    {
        when (productsUiState) {
            is ProductsUiState.Loading -> {
                Text(text = "Loading")
            }

            is ProductsUiState.Success -> {
                 val products = productsUiState.overallProduct.products

                Column(modifier = Modifier.fillMaxSize()){

                   Row(modifier =Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween) {
                       Text("Shop" , style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(1000),
                           fontFamily = FontFamily.Monospace),
                           modifier = Modifier.offset(10.dp))
                       Button(onClick = { },
                           colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                           Text(text = "Cart", style = TextStyle(color = Color.Gray,
                               fontSize = 20.sp, fontWeight = FontWeight.Bold))
                           Spacer(modifier = Modifier.width(7.dp))
                           Icon(painter = painterResource(id = R.drawable.outline_shopping_cart_24)
                               , contentDescription = "", tint = Color.Black)
                       }

                   }
                    
                    Spacer(modifier = Modifier.height(10.dp))
                    ShowContent(products)
                }

            }
            is ProductsUiState.Error -> {
                Text(text = "Error")
            }


        }
    }
}


@Composable
fun ShowContent(products : ProductBundle) {

    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
       // Monitors
        item { Text(text = "Monitors", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.monitor.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
                ) { Spacer(modifier = Modifier.height(50.dp))

                AsyncImage(model = products.monitor[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.monitor[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.monitor[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.monitor[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                       )
                    
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.monitor[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                            )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }

       // Monitors-Pro
        item { Text(text = "Monitors-Pro", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.monitorPro.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                AsyncImage(model = products.monitorPro[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.monitorPro[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.monitorPro[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.monitorPro[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.monitorPro[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }

        // Replacement Monitors

        item { Text(text = "Replacement Monitors", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.replacementMonitor.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                AsyncImage(model = products.replacementMonitor[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.replacementMonitor[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.replacementMonitor[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.replacementMonitor[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.replacementMonitor[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }

        // Transmissive Strips

        item { Text(text = "Transmissive Strips", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.transmissiveStrip.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                AsyncImage(model = products.transmissiveStrip[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.transmissiveStrip[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.transmissiveStrip[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.transmissiveStrip[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.transmissiveStrip[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }

        //Reflective Strips

        item { Text(text = "Reflective Strips", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.reflectiveStrip.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                AsyncImage(model = products.reflectiveStrip[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.reflectiveStrip[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.reflectiveStrip[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.reflectiveStrip[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.reflectiveStrip[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }

        //Reflective Strips 3T
        item { Text(text = "Reflective 3T Strips", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.reflective3TStrip.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
            ) { Spacer(modifier = Modifier.height(50.dp))

                AsyncImage(model = products.reflective3TStrip[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.reflective3TStrip[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.reflective3TStrip[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.reflective3TStrip[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.reflective3TStrip[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }

        // Clips
        item { Text(text = "Clips", style = TextStyle(fontSize = 32.sp , fontWeight = FontWeight.Bold)) }
        items(products.clip.size) { index ->

            Column(modifier = Modifier
                .fillMaxSize()
            ) { Spacer(modifier = Modifier.height(50.dp))

                AsyncImage(model = products.clip[index].imageUrl, contentDescription ="",
                    modifier = Modifier
                        .scale(1.5f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit, )
                Spacer(modifier = Modifier.height(25.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp))
                {
                    Text(text = products.clip[index].title, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text = "Rs. " + products.clip[index].price, style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Default))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                            containerColor = Color.Black), modifier = Modifier.width(120.dp)) {
                        Text(text = "Add" , fontSize = 18.sp)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Text(text =  products.clip[index].description, style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                        color = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
                            containerColor = Color.White), modifier = Modifier
                            .width(60.dp)
                            .weight(1f),
                        border = BorderStroke(width = 1.dp, color = Color.Gray)
                    ) {
                        Text(text = "Subscribe" , fontSize = 18.sp)
                    }

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp, bottom = 0.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically)
                {
                    Button(enabled = false, onClick = {},
                        shape = RoundedCornerShape(15.dp), modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Gray)
                    ) {
                        Text(text = products.clip[index].shippingInfo,
                            style = TextStyle(fontSize = 12.sp,
                                fontWeight = FontWeight.Normal, fontFamily = FontFamily.Default,
                                color = Color.DarkGray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 1.dp , color = Color.LightGray, modifier = Modifier.padding(15.dp,0.dp))


            }
        }


    }
}