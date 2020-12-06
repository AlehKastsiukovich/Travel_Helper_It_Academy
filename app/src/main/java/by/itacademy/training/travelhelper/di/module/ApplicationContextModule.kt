package by.itacademy.training.travelhelper.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private val context: Context) {

    @Provides
    fun context(): Context {
        return context.applicationContext
    }
}
