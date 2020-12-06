package by.itacademy.training.travelhelper.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationContextModule(private val context: Context) {

    @Named("applicationContext")
    @Provides
    fun context(): Context = context.applicationContext
}
