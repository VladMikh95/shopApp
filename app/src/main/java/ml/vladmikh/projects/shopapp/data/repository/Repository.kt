package ml.vladmikh.projects.shopapp.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ml.vladmikh.projects.shopapp.data.model_network.GoodsLatest
import ml.vladmikh.projects.shopapp.data.model_network.ProductOnDiscount
import ml.vladmikh.projects.shopapp.data.retrofit.RetrofitService
import retrofit2.Response

class Repository(private val retrofit: RetrofitService) {

    suspend fun getLatestProducts(): Response<GoodsLatest>{
        return retrofit.getLatestProducts()
    }

    suspend fun getProductsOnDiscont(): Response<ProductOnDiscount> {
        return retrofit.getProductsOnDiscount()
    }

}