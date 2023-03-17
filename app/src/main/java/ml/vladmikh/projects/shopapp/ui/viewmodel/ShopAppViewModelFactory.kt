package ml.vladmikh.projects.shopapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ml.vladmikh.projects.shopapp.data.repository.Repository


@Suppress("UNCHECKED_CAST")
class ShopAppViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ShopAppViewModel::class.java)) {
            return ShopAppViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

