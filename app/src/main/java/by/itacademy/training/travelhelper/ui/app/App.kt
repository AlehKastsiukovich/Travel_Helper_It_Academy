package by.itacademy.training.travelhelper.ui.app

import by.itacademy.training.travelhelper.di.component.ApplicationComponent
import by.itacademy.training.travelhelper.di.component.DaggerApplicationComponent
import by.itacademy.training.travelhelper.di.module.ApplicationContextModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("Not yet implemented")
    }
}
