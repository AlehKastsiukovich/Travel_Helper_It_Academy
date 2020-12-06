package by.itacademy.training.travelhelper.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private val context: Application) {

    @Provides
    fun context(): Context {
        return context
    }
}
