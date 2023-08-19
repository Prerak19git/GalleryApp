package com.prerak.galleryandroid.ui.theme

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OverallProduct(
    @SerialName(value = "products")
    val products : ProductBundle,
    @SerialName(value = "information")
    val information: Information
)


@Serializable
data class ProductBundle(
    @SerialName(value = "monitor")
    var monitor : List<Product>,
    @SerialName(value = "monitor-pro")
    var monitorPro : List<Product>,
    @SerialName(value = "replacement-monitor")
    var replacementMonitor : List<Product>,
    @SerialName(value = "transmissive-strip")
    var transmissiveStrip: List<Product>,
    @SerialName(value = "reflective-strip")
    var reflectiveStrip : List<Product>,
    @SerialName(value = "reflective_3T_strip")
    var reflective3TStrip : List<Product>,
    @SerialName(value = "clip")
    var clip : List<Product>

)
@Serializable
data class Information(
    @SerialName(value = "shop_message")
    val shopMessage :String
)

@Serializable
data class Product(
    @SerialName(value = "product_id")
    var productId : String,
    @SerialName(value = "checkout_url")
    var checkoutUrl : String,
    @SerialName(value = "title")
    var title : String,
    @SerialName(value = "price")
    var price : String,
    @SerialName(value = "description")
    var description : String,
    @SerialName(value = "shipping_info")
    var shippingInfo : String,
    @SerialName(value = "discounted_price")
    var discountedPrice: String,
    @SerialName(value = "image_url")
    var imageUrl : String,
    @SerialName(value = "button_text")
    var buttonText : String,
    @SerialName(value = "out_of_stock")
    var outOfStock: Boolean,

)