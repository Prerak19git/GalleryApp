package com.prerak.galleryandroid.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataProduct(
    @PrimaryKey(autoGenerate = true)
    val key : Int = 0,
    var productType :ProductType,
    var productId : String,
    var checkoutUrl : String,
    var title : String,
    var price : String,
    var description : String,
    var shippingInfo : String,
    var discountedPrice: String,
    var imageUrl : String,
    var buttonText : String,
    var outOfStock: Boolean,

)