package ml.vladmikh.projects.shopapp.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ml.vladmikh.projects.shopapp.data.model_network.FlashSale
import ml.vladmikh.projects.shopapp.data.model_network.GoodsLatest
import ml.vladmikh.projects.shopapp.data.model_network.Latest
import ml.vladmikh.projects.shopapp.data.model_network.ProductOnDiscount
import ml.vladmikh.projects.shopapp.data.repository.Repository
import ml.vladmikh.projects.shopapp.ui.modelui.Product
import retrofit2.Response

class ShopAppViewModel(private val repository: Repository) : ViewModel() {

    private var latestProduct = MutableLiveData<List<Product>>()
    private var productOnDiscont = MutableLiveData<List<Product>>()


    fun getLatestProduct(): MutableLiveData<List<Product>> {
        return latestProduct
    }

    fun getProductOnDiscont(): MutableLiveData<List<Product>> {
        return productOnDiscont
    }

    fun getDataAboutProductsFromNetwork() {

        try {
            viewModelScope.launch {

                val latest = async {
                    repository.getLatestProducts()
                }

                val flashSale = async {
                    repository.getProductsOnDiscont()
                }

                processData(latest.await(), flashSale.await())
            }

        } catch (e: Exception) {
            Log.e(ContentValues.TAG, e.message.toString())
        }
    }

    private fun processData(responseGoodsLatest: Response<GoodsLatest>, responseFlashSale: Response<ProductOnDiscount>) {

        val bodyLatest = responseGoodsLatest.body()
        val bodyFlashSale = responseFlashSale.body()

        var listLatest = ArrayList<Product>()
        var listProductOnDiscont = ArrayList<Product>()

        if (responseGoodsLatest.isSuccessful && bodyLatest != null) {
            listLatest = transformLatestToProduct((bodyLatest.latest))
        }

        if (responseFlashSale.isSuccessful && bodyFlashSale != null) {
            listProductOnDiscont = transformDiscontToProduct((bodyFlashSale.flash_sale))
        }

        latestProduct.postValue(listLatest)
        productOnDiscont.postValue(listProductOnDiscont)

    }

    private fun transformLatestToProduct(latests: List<Latest>): ArrayList<Product> {

        val products = ArrayList<Product>()

        for(latest in latests) {
            products.add(Product.Latest(latest.category, latest.image_url, latest.name, latest.price))
        }

        return products
    }

    private fun transformDiscontToProduct(flashSales: List<FlashSale>): ArrayList<Product> {

        val products = ArrayList<Product>()

        for(flashSale in flashSales) {
            products.add(Product.FlashSale(flashSale.category, flashSale.discount, flashSale.image_url, flashSale.name, flashSale.price))
        }

        return products
    }
}