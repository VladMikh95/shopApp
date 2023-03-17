package ml.vladmikh.projects.shopapp

import android.app.Application
import ml.vladmikh.projects.shopapp.data.di.AppComponent
import ml.vladmikh.projects.shopapp.data.di.DaggerAppComponent

class App : Application() {

    //lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

            component = DaggerAppComponent.builder().build()
    }
}
lateinit var component: AppComponent