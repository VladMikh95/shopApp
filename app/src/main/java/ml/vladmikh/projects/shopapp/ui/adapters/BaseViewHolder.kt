package ml.vladmikh.projects.shopapp.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ml.vladmikh.projects.shopapp.databinding.RecyclerviewLatestElementBinding
import ml.vladmikh.projects.shopapp.databinding.RecyclerviewSaleElementBinding
import ml.vladmikh.projects.shopapp.ui.modelui.Product

sealed class ShopAppViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class LatestViewHolder(private val binding: RecyclerviewLatestElementBinding) : ShopAppViewHolder(binding) {

        val imageView = binding.imageViewBackground

        fun bind(product: Product.Latest) {

            binding.textViewCategory.text = product.category
            binding.textViewName.text = product.name
            binding.textViewPrice.text = product.price.toString()
        }
    }

    class FlashSaleViewHolder(private val binding: RecyclerviewSaleElementBinding) : ShopAppViewHolder(binding) {

        val imageView = binding.imageViewBackground

        fun bind(product: Product.FlashSale) {

            binding.textViewCategory.text = product.category
            binding.textViewName.text = product.name
            binding.textViewPrice.text = product.price.toString()
            binding.textViewSale.text = product.discount.toString()
        }
    }

}