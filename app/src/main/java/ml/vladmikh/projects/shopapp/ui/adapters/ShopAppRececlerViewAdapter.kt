package ml.vladmikh.projects.shopapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ml.vladmikh.projects.shopapp.R
import ml.vladmikh.projects.shopapp.databinding.RecyclerviewLatestElementBinding
import ml.vladmikh.projects.shopapp.databinding.RecyclerviewSaleElementBinding
import ml.vladmikh.projects.shopapp.ui.modelui.Product

class ShopAppRecyclerViewAdapter  : RecyclerView.Adapter<ShopAppViewHolder>(){

    private var products = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAppViewHolder {

        return when(viewType) {

            R.layout.recyclerview_latest_element -> ShopAppViewHolder.LatestViewHolder(
                RecyclerviewLatestElementBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.recyclerview_sale_element -> ShopAppViewHolder.FlashSaleViewHolder(
                RecyclerviewSaleElementBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: ShopAppViewHolder, position: Int) {

        when (holder) {
            is ShopAppViewHolder.LatestViewHolder -> {
                val product = products[position] as Product.Latest
                holder.bind(product)
                Glide.with(holder.itemView.context)
                    .load(product.image_url)
                    .into(holder.imageView)
            }

            is ShopAppViewHolder.FlashSaleViewHolder -> {
                holder.bind(products[position] as Product.FlashSale)
                val product = products[position] as Product.FlashSale
                holder.bind(product)
                Glide.with(holder.itemView.context)
                    .load(product.image_url)
                    .into(holder.imageView)
            }
        }
    }

    override fun getItemCount(): Int = products.size


    override fun getItemViewType(position: Int): Int {
        return when(products[position]){
            is Product.Latest -> R.layout.recyclerview_latest_element
            is Product.FlashSale -> R.layout.recyclerview_sale_element
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun refreshProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

}

