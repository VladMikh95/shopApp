package ml.vladmikh.projects.shopapp.data.di

import dagger.Module
import dagger.Provides
import ml.vladmikh.projects.shopapp.data.repository.Repository
import ml.vladmikh.projects.shopapp.ui.viewmodel.ShopAppViewModelFactory

@Module
class ViewModelModule {

    @Provides
    fun providesShopAppViewModelFactory(repository: Repository) : ShopAppViewModelFactory {

        return ShopAppViewModelFactory(repository)
    }
}
