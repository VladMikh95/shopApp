package ml.vladmikh.projects.shopapp.data.di

import dagger.Component
import ml.vladmikh.projects.shopapp.MainActivity
import ml.vladmikh.projects.shopapp.ui.views.MainPageFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: MainPageFragment)
}