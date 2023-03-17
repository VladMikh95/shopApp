package ml.vladmikh.projects.shopapp.data.model_network

data class FlashSale(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)