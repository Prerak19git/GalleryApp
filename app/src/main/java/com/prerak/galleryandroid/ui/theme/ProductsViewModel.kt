package com.prerak.galleryandroid.ui.theme

import android.provider.ContactsContract.Data
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ProductsUiState{
    data class Success(val overallProduct: OverallProduct) : ProductsUiState
    object Error : ProductsUiState
    object Loading : ProductsUiState
}
class ProductsViewModel(
    private val dao: ProductDao
) :ViewModel() {

    private val _productsUiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val productsUiState = _productsUiState.asStateFlow()

    init {

        getProducts()
    }


    private fun getProducts() {
        viewModelScope.launch {
                _productsUiState.value =
                    try {
                        val listResult = ProductsApi.retrofitService.getProducts()

                        dao.deleteCompleteDatabase()
                        listResult.products.monitor.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.MONITOR,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )

                            dao.upsertDataProduct(newProduct)

                        }
                        listResult.products.monitorPro.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.MONITOR_PRO,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )

                            dao.upsertDataProduct(newProduct)

                        }
                        listResult.products.replacementMonitor.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.REPLACEMENT_MONITOR,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )

                            dao.upsertDataProduct(newProduct)

                        }
                        listResult.products.transmissiveStrip.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.TRANSMISSIVE_STRIP,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )

                            dao.upsertDataProduct(newProduct)

                        }
                        listResult.products.reflectiveStrip.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.REFLECTIVE_STRIP,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )

                            dao.upsertDataProduct(newProduct)

                        }
                        listResult.products.reflective3TStrip.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.REFLECTIVE_STRIP_3T,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )

                            dao.upsertDataProduct(newProduct)

                        }
                        listResult.products.clip.forEach()
                        { monitor ->
                            val newProduct = DataProduct(
                                productType = ProductType.CLIP,
                                productId = monitor.productId,
                                checkoutUrl = monitor.checkoutUrl,
                                title = monitor.title,
                                price = monitor.price,
                                description = monitor.description,
                                shippingInfo = monitor.shippingInfo,
                                discountedPrice = monitor.discountedPrice,
                                imageUrl = monitor.imageUrl,
                                buttonText = monitor.buttonText,
                                outOfStock = monitor.outOfStock,
                            )
                            dao.upsertDataProduct(newProduct)

                        }


                        ProductsUiState.Success(listResult)

                    } catch (e: IOException) {
                        if(dao.isEmpty()){ ProductsUiState.Error }
                        else
                        {
                            val monitors = mutableListOf<Product>()
                            val monitorsPro = mutableListOf<Product>()

                            val replacementMonitors = mutableListOf<Product>()
                            val transmissiveStrips = mutableListOf<Product>()
                            val reflectiveStrips = mutableListOf<Product>()
                            val reflectiveStrips3T = mutableListOf<Product>()
                            val clips = mutableListOf<Product>()

                            dao.getTheDataBase().forEach()
                            {
                                when(it.productType)
                                {
                                    ProductType.MONITOR -> {
                                        monitors.add(convertToProduct(it))
                                    }
                                    ProductType.MONITOR_PRO -> {
                                        monitorsPro.add(convertToProduct(it))
                                    }
                                    ProductType.REPLACEMENT_MONITOR -> {
                                        replacementMonitors.add(convertToProduct(it))
                                    }
                                    ProductType.TRANSMISSIVE_STRIP -> {
                                        transmissiveStrips.add(convertToProduct(it))
                                    }
                                    ProductType.REFLECTIVE_STRIP -> {
                                        reflectiveStrips.add(convertToProduct(it))
                                    }
                                    ProductType.REFLECTIVE_STRIP_3T -> {
                                        reflectiveStrips3T.add(convertToProduct(it))
                                    }
                                    ProductType.CLIP -> {
                                        clips.add(convertToProduct(it))
                                    }
                                }
                            }

                            val productOfBundle = ProductBundle(
                                monitor = monitors.toList(),
                                monitorPro = monitorsPro,
                                replacementMonitor = replacementMonitors,
                                transmissiveStrip = transmissiveStrips,
                                reflectiveStrip = reflectiveStrips,
                                reflective3TStrip = reflectiveStrips3T,
                                clip = clips
                            )
                            val infoToPass = Information(
                                shopMessage = ""
                            )

                            ProductsUiState.Success(OverallProduct(productOfBundle,
                                infoToPass))

                        }

                    }



        }
    }
}

fun convertToProduct( dataProduct : DataProduct):Product
{
    return Product(
        productId = dataProduct.productId,
        checkoutUrl = dataProduct.checkoutUrl,
        title = dataProduct.title,
        price = dataProduct.price,
        description = dataProduct.description,
        shippingInfo = dataProduct.shippingInfo,
        discountedPrice = dataProduct.discountedPrice,
        imageUrl = dataProduct.imageUrl,
        outOfStock = dataProduct.outOfStock,
        buttonText = dataProduct.buttonText
    )
}
