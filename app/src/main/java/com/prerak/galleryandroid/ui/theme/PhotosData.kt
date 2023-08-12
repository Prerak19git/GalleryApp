package com.prerak.galleryandroid.ui.theme

import android.media.Image
import androidx.compose.ui.graphics.painter.Painter

data class Date(
    var day: Int,
    var month: Int,
    var year: Int,

)
data class PhotosData(
    var imageResource : Painter,
    var date : Date

)