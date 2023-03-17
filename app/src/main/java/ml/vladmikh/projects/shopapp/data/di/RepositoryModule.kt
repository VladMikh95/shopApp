package ml.vladmikh.projects.shopapp.data.di

import dagger.Module
import dagger.Provides
import ml.vladmikh.projects.shopapp.data.repository.Repository
import ml.vladmikh.projects.shopapp.data.retrofit.RetrofitService
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(retrofitService: RetrofitService): Repository {

        return Repository(retrofitService)
    }
}