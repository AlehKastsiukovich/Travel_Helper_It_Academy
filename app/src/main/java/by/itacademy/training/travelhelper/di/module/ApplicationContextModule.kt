package by.itacademy.training.travelhelper.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationContextModule(private val context: Context) {

    @Singleton
    @Named("applicationContext")
    @Provides
    fun context(): Context = context.applicationContext
}
