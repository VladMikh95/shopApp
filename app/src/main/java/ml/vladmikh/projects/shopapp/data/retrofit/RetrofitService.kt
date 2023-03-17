package ml.vladmikh.projects.shopapp.data.retrofit

import ml.vladmikh.projects.shopapp.data.model_network.GoodsLatest
import ml.vladmikh.projects.shopapp.data.model_network.ProductOnDiscount
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestProducts(): Response<GoodsLatest>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getProductsOnDiscount(): Response<ProductOnDiscount>

}