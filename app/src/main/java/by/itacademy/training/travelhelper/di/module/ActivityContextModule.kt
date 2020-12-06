package by.itacademy.training.travelhelper.di.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityContextModule(private val context: Activity) {

    @Named("activityContext")
    @Provides
    fun context(): Context = context
}
