package by.itacademy.training.travelhelper.di.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ActivityContextModule(private val context: Activity) {

    @Singleton
    @Named("activityContext")
    @Provides
    fun provideContext(): Context = context
}
