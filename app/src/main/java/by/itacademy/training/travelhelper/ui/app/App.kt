package by.itacademy.training.travelhelper.ui.app

import android.app.Application
import by.itacademy.training.travelhelper.di.component.ApplicationComponent
import by.itacademy.training.travelhelper.di.component.DaggerApplicationComponent
import by.itacademy.training.travelhelper.di.module.ApplicationContextModule

class App : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }
}
